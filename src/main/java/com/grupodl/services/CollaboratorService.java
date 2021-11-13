package com.grupodl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupodl.models.Collaborator;
import com.grupodl.repositories.CollaboratorRepository;

@Service
public class CollaboratorService {
	
	@Autowired
	CollaboratorRepository collaboratorRepository;
	
	public List<Collaborator> findAllCollaborator(){
		return collaboratorRepository.findAllCollaborator();
	}
	
	public Collaborator findCollaboratorByCpf(String cpf){
		return collaboratorRepository.findCollaboratorByCPF(cpf);
	}
	
	

}
