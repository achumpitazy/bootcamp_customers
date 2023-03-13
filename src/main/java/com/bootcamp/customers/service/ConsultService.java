package com.bootcamp.customers.service;

import com.bootcamp.customers.dto.Movements;
import com.bootcamp.customers.dto.Products;

import reactor.core.publisher.Mono;

public interface ConsultService {

	Mono<Products> productXCustomerIdPerson(String customerId);
	
	Mono<Products> productXCustomerIdCompany(String customerId);

	Mono<Movements> movementXAccountId(String id);
	
	Mono<Movements> movementXCreditId(String id);
	
	Mono<Movements> movementXCreditCardId(String id);

}
