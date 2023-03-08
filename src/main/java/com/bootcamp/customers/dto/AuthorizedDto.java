package com.bootcamp.customers.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthorizedDto {
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String dni;
	@NotEmpty
	private String email;
	@NotEmpty
	private String telephone;
}
