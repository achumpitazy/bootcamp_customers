package com.bootcamp.customers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.customers.clients.AccountsRestClient;
import com.bootcamp.customers.clients.CreditsRestClient;
import com.bootcamp.customers.clients.TransactionsRestClient;
import com.bootcamp.customers.dto.Account;
import com.bootcamp.customers.dto.Credit;
import com.bootcamp.customers.dto.CreditCard;
import com.bootcamp.customers.dto.Customer;
import com.bootcamp.customers.dto.Movements;
import com.bootcamp.customers.dto.Products;
import com.bootcamp.customers.dto.Transaction;
import com.bootcamp.customers.repository.CompanyRepository;
import com.bootcamp.customers.repository.PersonRepository;
import com.bootcamp.customers.service.ConsultService;

import reactor.core.publisher.Mono;

@Service
public class ConsultServiceImpl implements ConsultService{

	@Autowired
	AccountsRestClient accountsRestClient;
	
	@Autowired
	CreditsRestClient creditsRestClient;
	
	@Autowired
	TransactionsRestClient transactionsRestClient;
	
	@Autowired
    private PersonRepository personRepository;
	
	@Autowired
    private CompanyRepository companyRepository;
	
	@Override
	public Mono<Products> productXCustomerIdPerson(String customerId) {
        return personRepository.findById(customerId).flatMap(p -> {
        	Customer customer = new Customer();
        	customer.setId(p.getId());
			customer.setDocument(p.getDni());
	        customer.setNameCustomer(p.getName().concat(" ").concat(p.getLastName()));
	        customer.setTypeCustomer(p.getTypeCustomer());
        	return obtainProducts(customer, customerId);
        });
	}

	@Override
	public Mono<Products> productXCustomerIdCompany(String customerId) {
		return companyRepository.findById(customerId).flatMap(p -> {
			Customer customer = new Customer();
			customer.setId(p.getId());
			customer.setDocument(p.getRuc());
	        customer.setNameCustomer(p.getBusinessName());
	        customer.setTypeCustomer(p.getTypeCustomer());
        	return obtainProducts(customer, customerId);
        });
	}
	
	private Mono<Products> obtainProducts(Customer customer, String customerId){
		List<Account> listAccounts = new ArrayList<>();
        List<Credit> listCredits = new ArrayList<>();
        List<CreditCard> listCreditCards = new ArrayList<>();
        return accountsRestClient.getAllAccountXCustomerId(customerId).collectList().flatMap(a -> {
        	listAccounts.addAll(a);
        	return creditsRestClient.getAllCreditXCustomerId(customerId).collectList().flatMap(c -> {
        		listCredits.addAll(c);
        		return creditsRestClient.getAllCreditCardXCustomerId(customerId).collectList().flatMap(cc -> {
        			listCreditCards.addAll(cc);
        			return Mono.just(new Products(customer, listAccounts, listCredits, listCreditCards));
        		});
        	});
        });
	}

	@Override
	public Mono<Movements> movementXAccountId(String id) {
		List<Transaction> listTransaction = new ArrayList<>();
		return transactionsRestClient.getAllXProductId(id).collectList().flatMap(t ->{
			listTransaction.addAll(t);
			return accountsRestClient.getAccountById(id).flatMap(a -> {
				return obtainCustomer(listTransaction, a.getCustomerId(), a.getTypeCustomer());
			});
		});	
	};
	
	@Override
	public Mono<Movements> movementXCreditId(String id) {
		List<Transaction> listTransaction = new ArrayList<>();
		return transactionsRestClient.getAllXProductId(id).collectList().flatMap(t ->{
			listTransaction.addAll(t);
			return creditsRestClient.getCreditById(id).flatMap(a -> {
				return obtainCustomer(listTransaction, a.getCustomerId(), a.getTypeCustomer());
			});
		});	
	};
	
	@Override
	public Mono<Movements> movementXCreditCardId(String id) {
		List<Transaction> listTransaction = new ArrayList<>();
		return transactionsRestClient.getAllXProductId(id).collectList().flatMap(t ->{
			listTransaction.addAll(t);
			return creditsRestClient.getCreditCardById(id).flatMap(a -> {
				return obtainCustomer(listTransaction, a.getCustomerId(), a.getTypeCustomer());
			});
		});	
	};
	
	private Mono<Movements> obtainCustomer(List<Transaction> listTransaction, String id, String type){
		if(type.equals("PERSON")) {
			return personRepository.findById(id).flatMap(p -> {
				Customer customer = new Customer();
				customer.setDocument(p.getDni());
				customer.setNameCustomer(p.getName().concat(" ").concat(p.getLastName()));
				customer.setTypeCustomer(p.getTypeCustomer());
				return Mono.just(new Movements(customer, listTransaction));
			});
		}else {
			return companyRepository.findById(id).flatMap(p -> {
				Customer customer = new Customer();
				customer.setDocument(p.getRuc());
				customer.setNameCustomer(p.getBusinessName());
				customer.setTypeCustomer(p.getTypeCustomer());
				return Mono.just(new Movements(customer, listTransaction));
			});
		}
	}

}
