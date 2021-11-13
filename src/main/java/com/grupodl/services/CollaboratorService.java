package com.grupodl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupodl.models.Collaborator;
import com.grupodl.models.Food;
import com.grupodl.repositories.CollaboratorRepository;
import com.grupodl.repositories.FoodsRepository;

@Service
public class CollaboratorService {

	@Autowired
	CollaboratorRepository collaboratorRepository;
	@Autowired
	FoodsRepository foodsRepository;

	public List<Collaborator> findAllCollaborator() {
		return collaboratorRepository.findAllCollaborator();
	}

	public Collaborator findCollaboratorByCpf(String cpf) {
		return collaboratorRepository.findCollaboratorByCPF(cpf);
	}

	public void insertBreakfast(Collaborator collaborator) {
		
		foodExist(collaborator);

		collaboratorRepository.registerCollaborator(collaborator.getCpf(), collaborator.getName());
		for (Food food : collaborator.getFoods()) {

			foodsRepository.registerFoods(food.getName(), collaborator.getCpf());
		}

	}

	public void updateBreakfast(Collaborator collaborator, String cpf) {
		
		foodExist(collaborator);
		
		
			for (Food f : collaborator.getFoods()) {
				foodsRepository.updateFood(f.getId(), f.getName());
			}
			collaboratorRepository.updateCollaborator(cpf, collaborator.getName());
	}

	public void deleteBreakfast(String cpf) {

		Collaborator collaborator = collaboratorRepository.findCollaboratorByCPF(cpf);

		for (Food e : collaborator.getFoods()) {
			foodsRepository.deleteFood(e.getId());
		}
		collaboratorRepository.deleteCollaborator(cpf);
	}

	public boolean foodExist(Collaborator collaborator) {
		List<Food> foodList = foodsRepository.findAllFoods();
		for (Food food_database : foodList) {
			for (Food food : collaborator.getFoods()) {
				if (food_database.getName().toUpperCase().trim().equals(food.getName().toUpperCase().trim())) {
					// todo add Exception Custom
					System.out.println(
							"---------------------------------" + food.getName() + "---------------------------------");
					throw new NullPointerException();
				}
			}
		}
		return false;
	}
}
