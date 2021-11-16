package com.grupodl.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupodl.models.Collaborator;
import com.grupodl.models.Food;
import com.grupodl.repositories.CollaboratorRepository;
import com.grupodl.repositories.FoodsRepository;


@Component
public class DummyData {
	
	@Autowired
	CollaboratorRepository collaboratorRepository; 
	
	@Autowired
	FoodsRepository foodsRepository; 
	
	//@PostConstruct 
	public void test() {
		
		Collaborator c1 = new Collaborator(null, "100.100.100-10", "Carlos");		
		Collaborator c2 = new Collaborator(null, "300.300.300-30", "Mario");
		Collaborator c3 = new Collaborator(null, "400.400.400-40", "Alfredo");
		
		collaboratorRepository.registerCollaborator(c1.getCpf(),c1.getName());
		collaboratorRepository.registerCollaborator(c2.getCpf(),c2.getName());
		collaboratorRepository.registerCollaborator(c3.getCpf(),c3.getName());
		
		
		Food f1 = new Food(null, "Pão",c1);
		Food f2 = new Food(null, "Café",c2);
		Food f3 = new Food(null, "Leite",c3);
		Food f4 = new Food(null, "Margarina",c1);
		Food f5 = new Food(null, "Doce de Leite",c2);
		
		Collaborator c1A = collaboratorRepository.findCollaboratorByCPF(c1.getCpf());
		Collaborator c2A = collaboratorRepository.findCollaboratorByCPF(c2.getCpf());
		Collaborator c3A = collaboratorRepository.findCollaboratorByCPF(c3.getCpf());		
		
		foodsRepository.registerFoods(f1.getName(), c1A.getId());
		foodsRepository.registerFoods(f2.getName(), c2A.getId());
		foodsRepository.registerFoods(f3.getName(), c3A.getId());
		foodsRepository.registerFoods(f4.getName(), c1A.getId());
		foodsRepository.registerFoods(f5.getName(), c2A.getId());
								
		
	}

}
