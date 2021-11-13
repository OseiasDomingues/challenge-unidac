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
	
	@PostConstruct
	public void test() {
		
		Collaborator c1 = new Collaborator("10010010010", "Carlos");		
		Collaborator c2 = new Collaborator("30030030030", "Mario");
		Collaborator c3 = new Collaborator("40040040040", "Alfredo");
		
		collaboratorRepository.registerCollaborator(c1.getCpf(),c1.getName());
		collaboratorRepository.registerCollaborator(c2.getCpf(),c2.getName());
		collaboratorRepository.registerCollaborator(c3.getCpf(),c3.getName());
		
		Food f1 = new Food(null, "Pão",c1);
		Food f2 = new Food(null, "Café",c2);
		Food f3 = new Food(null, "Leite",c3);
		Food f4 = new Food(null, "Margarina",c1);
		Food f5 = new Food(null, "Doce de Leite",c2);
		
		foodsRepository.registerFoods(f1.getName(), c1.getCpf());
		foodsRepository.registerFoods(f2.getName(), c2.getCpf());
		foodsRepository.registerFoods(f3.getName(), c3.getCpf());
		foodsRepository.registerFoods(f4.getName(), c1.getCpf());
		foodsRepository.registerFoods(f5.getName(), c2.getCpf());
								
		
	}

}
