package com.grupodl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupodl.models.Collaborator;
import com.grupodl.repositories.FoodsRepository;

@Service
public class FoodsService {
	
	@Autowired
	FoodsRepository foodsRepository;
	
	public List<Collaborator> findAllCollaborator(){
		return foodsRepository.findAllFoods();
	}
	
	public Collaborator findCollaboratorByCpf(Long id){
		return foodsRepository.findFoodById(id);
	}
	
	

}
