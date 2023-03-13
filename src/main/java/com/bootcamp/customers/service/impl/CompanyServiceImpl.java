package com.bootcamp.customers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.customers.dto.CompanyRequestDto;
import com.bootcamp.customers.dto.Message;
import com.bootcamp.customers.entity.Company;
import com.bootcamp.customers.repository.CompanyRepository;
import com.bootcamp.customers.service.CompanyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase de implementación para la interfaz CompanyService
 */
@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
    private CompanyRepository companyRepository;

	/**
	 * Método que devuelve todos los clientes de tipo empresarial dentro el repositorio.
	 * @return Flux<Company>
	 */
	@Override
	public Flux<Company> getAll() {
		return companyRepository.findAll();
	}

	/**
	 * Devuelve todos los clientes de tipo empresarial dentro el repositorio segun el id del cliente.
	 * @return Mono<Company>
	 */
	@Override
	public Mono<Company> getCompanyById(String companyId) {
		return companyRepository.findById(companyId);
	}

	/**
	 * Crea un cliente de tipo empresarial dentro del repositorio con los datos enviados en el body.
	 * @param companyRequestDto
	 * @return Mono<Company>
	 */
	@Override
	public Mono<Company> createCompany(CompanyRequestDto companyRequestDto) {
		Company company = new Company(null, companyRequestDto.getBusinessName(), companyRequestDto.getRuc(), companyRequestDto.getEmail()
				, companyRequestDto.getTelephone() , companyRequestDto.getHeadlines(), companyRequestDto.getSignatories(),"COMPANY");
		return companyRepository.save(company);
	}

	/**
	 * Actualiza un cliente de tipo empresarial dentro del repositorio segun los datos enviados en el body.
	 * @param companyRequestDto
	 * @return Mono<Company>
	 */
	@Override
	public Mono<Company> updateCompany(CompanyRequestDto companyRequestDto) {
		return companyRepository.findById(companyRequestDto.getId())
                .flatMap(uCompany -> {
                	uCompany.setBusinessName(companyRequestDto.getBusinessName());
                	uCompany.setRuc(companyRequestDto.getRuc());
                	uCompany.setEmail(companyRequestDto.getEmail());
                	uCompany.setTelephone(companyRequestDto.getTelephone());
                	uCompany.setHeadlines(companyRequestDto.getHeadlines());
                	uCompany.setSignatories(companyRequestDto.getSignatories());
                	uCompany.setTypeCustomer("COMPANY");
                     return companyRepository.save(uCompany);
        });
	}

	/**
	 * Elimina al cliente de tipo empresarial dentro del repositorio segun el id del cliente.
	 * @param companyId
	 * @return Mono<Message>
	 */
	@Override
	public Mono<Message> deleteCompany(String companyId) {
		Message message = new Message("Company does not exist");
		return companyRepository.findById(companyId)
                .flatMap(dCompany -> {
                	message.setMessage("Company deleted successfully");
                	return companyRepository.deleteById(dCompany.getId()).thenReturn(message);
        }).defaultIfEmpty(message);
	}

}
