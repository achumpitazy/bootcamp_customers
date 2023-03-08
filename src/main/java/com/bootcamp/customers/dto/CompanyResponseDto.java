package com.bootcamp.customers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDto{
//	private CompanyResponseDto id;
//	private String businessName;
//	private String ruc;
//	private String email;
//	private String telephone;
//	private List<AuthorizedDto> headlines;
//	private List<AuthorizedDto> signatories;
	private CompanyRequestDto companyRequestDto;
	private Message statusDto;
}
