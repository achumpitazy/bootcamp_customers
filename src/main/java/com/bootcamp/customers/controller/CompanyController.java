package com.bootcamp.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.customers.dto.CompanyRequestDto;
import com.bootcamp.customers.dto.Message;
import com.bootcamp.customers.entity.Company;
import com.bootcamp.customers.service.CompanyService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
    private CompanyService companyService;
	
	@GetMapping
    public Flux<Company> getAll(){
		return companyService.getAll();
    }
	
	@GetMapping("/{companyId}")
    public Mono<Company> getCompanyById(@PathVariable String companyId){
		return companyService.getCompanyById(companyId);
    }
	
	@PostMapping
    public Mono<Company> createCompany(@Valid @RequestBody CompanyRequestDto companyRequestDto){
		return companyService.createCompany(companyRequestDto);
    }
	
	@PutMapping
	public Mono<Company> updateCompany(@RequestBody CompanyRequestDto companyRequestDto){
		return companyService.updateCompany(companyRequestDto);
    }
	
	@DeleteMapping("/{companyId}")
	public Mono<Message> deleteCompany(@PathVariable String companyId){
		return companyService.deleteCompany(companyId);
    }
	
}
