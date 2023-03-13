package com.bootcamp.customers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDto{
	private CompanyRequestDto companyRequestDto;
	private Message statusDto;
}
