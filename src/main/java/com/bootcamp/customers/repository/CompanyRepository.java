package com.bootcamp.customers.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.customers.entity.Company;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {

}
