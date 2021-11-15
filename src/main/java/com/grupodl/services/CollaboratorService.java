package com.grupodl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupodl.models.Collaborator;
import com.grupodl.models.Food;
import com.grupodl.repositories.CollaboratorRepository;
import com.grupodl.repositories.FoodsRepository;
import com.grupodl.services.exceptions.FieldInvalidException;
import com.grupodl.services.exceptions.ResourceAlreadyExistsException;
import com.grupodl.services.exceptions.ResourceNotFoundException;

@Service
public class CollaboratorService {

	@Autowired
	CollaboratorRepository collaboratorRepository;
	@Autowired
	FoodsRepository foodsRepository;

	public List<Collaborator> findAllCollaborator() {
		return collaboratorRepository.findAllCollaborator();
	}

	public Collaborator findCollaboratorById(Long id) {
		Collaborator collaborator = collaboratorRepository.findCollaboratorById(id);
		if(collaborator == null) {
			throw new ResourceNotFoundException(id);
		}
		return collaborator;
	}

	public void insertBreakfast(Collaborator collaborator) {
		foodExist(collaborator);
		cpfExistInsert(collaborator.getCpf());

		collaboratorRepository.registerCollaborator(collaborator.getCpf(), collaborator.getName());
		
		Collaborator collaboratorSave = collaboratorRepository.findCollaboratorByCPF(collaborator.getCpf());
		
		int i = 1;
		
		for (Food food : collaborator.getFoods()) {
			
			if(!food.getName().isEmpty()) {
			foodsRepository.registerFoods(food.getName(), collaboratorSave.getId());	
			}else {
				if(i >= collaborator.getFoods().size()) {
					throw new FieldInvalidException("Não há itens na lista");
				}
				i++;		
				 
			}
		}

	}

	public void updateBreakfast(Collaborator collaborator, Long id) {

		foodExist(collaborator);
		cpfExistUpdate(collaborator.getCpf(),id);

		for (Food f : collaborator.getFoods()) {
			if(collaborator.getName().isEmpty() || f.getId() == null || f.getName().isBlank()) {
				throw new FieldInvalidException("Algum campo está invalido!");
			}
			foodsRepository.updateFood(f.getId(), f.getName());
		}
		collaboratorRepository.updateCollaborator(id, collaborator.getName(),collaborator.getCpf());
	}

	public void deleteBreakfast(Long id) {

		Collaborator collaborator = collaboratorRepository.findCollaboratorById(id);
		if(collaborator == null) {
			throw new ResourceNotFoundException(id);
		}

		for (Food e : collaborator.getFoods()) {
			foodsRepository.deleteFood(e.getId());
		}
		collaboratorRepository.deleteCollaborator(id);
	}

	public void foodExist(Collaborator collaborator) {
		List<Food> foodList = foodsRepository.findAllFoods();
		for (Food food_database : foodList) {
			for (Food food : collaborator.getFoods()) {
				if (food_database.getName().toUpperCase().trim().equals(food.getName().toUpperCase().trim())) {
					
					if(collaborator.getId() != food_database.getCollaborator().getId()) {
					throw new ResourceAlreadyExistsException("O item " + food.getName() + " já existe!");				
					}
			}}
		}
	}


	private void cpfExistInsert(String cpf) {
		List<Collaborator> collaboratorList = collaboratorRepository.findAllCollaborator();
		for (Collaborator collaborator_database : collaboratorList) {			
				if (collaborator_database.getCpf().equals(cpf)) {						
					throw new ResourceAlreadyExistsException("O CPF " + cpf + " já está registrado");				
							}
		}
		
	}
	private void cpfExistUpdate(String cpf,Long id) {
		List<Collaborator> collaboratorList = collaboratorRepository.findAllCollaborator();
		Collaborator collaboratorCpfTest = collaboratorRepository.findCollaboratorById(id);
		for (Collaborator collaborator_database : collaboratorList) {			
				if (collaborator_database.getCpf().equals(cpf)) {
					if(collaboratorCpfTest.getId() != collaborator_database.getId()) {
						
					throw new ResourceAlreadyExistsException("O CPF " + cpf + " já está registrado");
					
				}
			}
		}
		
	}
}

