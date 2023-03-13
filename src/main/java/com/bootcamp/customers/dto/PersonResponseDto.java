package com.bootcamp.customers.dto;

import com.bootcamp.customers.entity.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDto{
	private Person person;
	private Message statusDto;
}
