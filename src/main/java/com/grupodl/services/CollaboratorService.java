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
		if (collaborator == null) {
			throw new ResourceNotFoundException(id);
		}
		return collaborator;
	}

	public void insertBreakfast(Collaborator collaborator) {
		foodDouble(collaborator);
		foodExist(collaborator);
		cpfExistInsert(collaborator.getCpf());

		collaboratorRepository.registerCollaborator(collaborator.getCpf(), collaborator.getName());

		Collaborator collaboratorSave = collaboratorRepository.findCollaboratorByCPF(collaborator.getCpf());

		int i = 1;

		for (Food food : collaborator.getFoods()) {
			if (!food.getName().isEmpty()) {
				foodsRepository.registerFoods(food.getName(), collaboratorSave.getId());
			} else {
				if (i >= collaborator.getFoods().size()) {
					collaboratorRepository.deleteCollaborator(collaboratorSave.getId());
					throw new FieldInvalidException("Preencher pelos menos um item da lista");
				}
				i++;

			}
		}

	}

	public void updateBreakfast(Collaborator collaborator, Long id) {
		foodDouble(collaborator);
		foodExist(collaborator);
		cpfExistUpdate(collaborator.getCpf(), id);

		for (Food f : collaborator.getFoods()) {
			if (collaborator.getName().isEmpty() || f.getId() == null || f.getName().isBlank()) {
				throw new FieldInvalidException("Algum campo esta invalido!");
			}
			foodsRepository.updateFood(f.getId(), f.getName());
		}
		collaboratorRepository.updateCollaborator(id, collaborator.getName(), collaborator.getCpf());
	}

	public void deleteBreakfast(Long id) {

		Collaborator collaborator = collaboratorRepository.findCollaboratorById(id);
		if (collaborator == null) {
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
					if (collaborator.getId() != food_database.getCollaborator().getId()) {
						throw new ResourceAlreadyExistsException("O item " + food.getName() + " ja existe!");
					}
				}
			}
		}
	}

	private void cpfExistInsert(String cpf) {
		List<Collaborator> collaboratorList = collaboratorRepository.findAllCollaborator();
		for (Collaborator collaborator_database : collaboratorList) {
			if (collaborator_database.getCpf().equals(cpf)) {
				throw new ResourceAlreadyExistsException("O CPF " + cpf + " ja está registrado");
			}
		}

	}

	private void cpfExistUpdate(String cpf, Long id) {
		List<Collaborator> collaboratorList = collaboratorRepository.findAllCollaborator();
		Collaborator collaboratorCpfTest = collaboratorRepository.findCollaboratorById(id);
		for (Collaborator collaborator_database : collaboratorList) {
			if (collaborator_database.getCpf().equals(cpf)) {
				if (collaboratorCpfTest.getId() != collaborator_database.getId()) {
					throw new ResourceAlreadyExistsException("O CPF " + cpf + " ja está registrado");

				}
			}
		}

	}

	private void foodDouble(Collaborator collaborator) {
		for (Food x : collaborator.getFoods()) {
			int i = 1;
			for (Food y : collaborator.getFoods()) {
				if (x.getName().toUpperCase().trim().equals(y.getName().toUpperCase().trim())) {
					if (i >= 2 && !x.getName().isBlank()) {
						throw new ResourceAlreadyExistsException("O item " + x.getName() + " está duplicado!");
					}
					i++;
				}
			}
		}
	}
}
