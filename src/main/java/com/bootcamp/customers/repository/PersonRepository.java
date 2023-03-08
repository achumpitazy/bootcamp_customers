package com.bootcamp.customers.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.customers.entity.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

}
