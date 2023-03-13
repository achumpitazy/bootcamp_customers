package com.bootcamp.customers.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase de transferencia de datos para la respuesta
 * de los clientes empresariales
 */
@Getter
@Setter
public class CompanyResponseDto{
	private CompanyRequestDto companyRequestDto;
	private Message statusDto;
}
