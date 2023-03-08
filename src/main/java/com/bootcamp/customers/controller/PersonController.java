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

import com.bootcamp.customers.dto.Message;
import com.bootcamp.customers.dto.PersonRequestDto;
import com.bootcamp.customers.entity.Person;
import com.bootcamp.customers.service.PersonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
    private PersonService personService;
	
	@GetMapping
    public Flux<Person> getAll(){
		return personService.getAll();
    }
	
	@GetMapping("/{personId}")
    public Mono<Person> getPersonById(@PathVariable String personId){
		return personService.getPersonById(personId);
    }
	
	@PostMapping
    public Mono<Person> createPerson(@RequestBody PersonRequestDto personRequestDto){
		return personService.createPerson(personRequestDto);
    }
	
	@PutMapping
	public Mono<Person> updatePerson(@RequestBody PersonRequestDto personRequestDto){
		return personService.updatePerson(personRequestDto);
    }
	
	@DeleteMapping("/{personId}")
	public Mono<Message> deletePerson(@PathVariable String personId){
		return personService.deletePerson(personId);
    }
	
}
