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
		cpfExist(collaborator.getCpf());

		collaboratorRepository.registerCollaborator(collaborator.getCpf(), collaborator.getName());
		
		Collaborator collaboratorSave = collaboratorRepository.findCollaboratorByCPF(collaborator.getCpf());
		
		for (Food food : collaborator.getFoods()) {

			foodsRepository.registerFoods(food.getName(), collaboratorSave.getId());			
		}

	}

	public void updateBreakfast(Collaborator collaborator, Long id) {

		foodExist(collaborator);

		for (Food f : collaborator.getFoods()) {
			if(collaborator.getName().isEmpty() || f.getId() == null || f.getName().isBlank()) {
				throw new FieldInvalidException("Algum campo está invalido!");
			}
			foodsRepository.updateFood(f.getId(), f.getName());
		}
		collaboratorRepository.updateCollaborator(id, collaborator.getName());
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
					throw new ResourceAlreadyExistsException("O item " + food.getName() + " já existe!");
				}
			}
		}
	}


	private void cpfExist(String cpf) {
		List<Collaborator> collaboratorList = collaboratorRepository.findAllCollaborator();
		for (Collaborator collaborator_database : collaboratorList) {			
				if (collaborator_database.getCpf().equals(cpf)) {
					throw new ResourceAlreadyExistsException("O CPF " + cpf + " já está registrado");
				}
			}
		}
		
	}

