package com.bootcamp.customers.dto;

import com.bootcamp.customers.entity.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDto{
//	private String id;
//	private String name;
//	private String lastName;
//	private String dni;
//	private String email;
//	private String telephone;
	private Person person;
	private Message statusDto;
}
