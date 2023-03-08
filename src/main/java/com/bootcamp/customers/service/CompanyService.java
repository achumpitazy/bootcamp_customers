package com.bootcamp.customers.service;

import com.bootcamp.customers.dto.CompanyRequestDto;
import com.bootcamp.customers.dto.Message;
import com.bootcamp.customers.entity.Company;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {

	Flux<Company> getAll();

	Mono<Company> getCompanyById(String companyId);

	Mono<Company> createCompany(CompanyRequestDto companyRequestDto);

	Mono<Company> updateCompany(CompanyRequestDto companyRequestDto);

	Mono<Message> deleteCompany(String companyId);

}
