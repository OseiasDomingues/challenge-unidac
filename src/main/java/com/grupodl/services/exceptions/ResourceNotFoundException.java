package com.grupodl.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String cpf) {
		super("CPF " + cpf + " não encontrado.");
	}
	
	public ResourceNotFoundException(Long id) {
		super("ID " + id + " não encontrado.");
	}
	

}
