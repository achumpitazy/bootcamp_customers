package com.bootcamp.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.customers.dto.Movements;
import com.bootcamp.customers.dto.Products;
import com.bootcamp.customers.service.ConsultService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consult")
public class ConsultController {
	
	@Autowired
    private ConsultService consultService;
		
	@GetMapping("/person/{customerId}")
    public Mono<Products> productXCustomerIdPerson(@PathVariable String customerId){
		return consultService.productXCustomerIdPerson(customerId);
    }
	
	@GetMapping("/company/{customerId}")
    public Mono<Products> productXCustomerIdCompany(@PathVariable String customerId){
		return consultService.productXCustomerIdCompany(customerId);
    }
	
	@GetMapping("/movementaccount/{id}")
    public Mono<Movements> movementXAccountId(@PathVariable String id){
		return consultService.movementXAccountId(id);
    }
	
	@GetMapping("/movementcredit/{id}")
    public Mono<Movements> movementXCreditId(@PathVariable String id){
		return consultService.movementXCreditId(id);
    }
	
	@GetMapping("/movementcreditcard/{id}")
    public Mono<Movements> movementXCreditCardId(@PathVariable String id){
		return consultService.movementXCreditCardId(id);
    }
	
}
