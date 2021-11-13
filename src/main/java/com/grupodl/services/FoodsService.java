package com.grupodl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupodl.models.Food;
import com.grupodl.repositories.FoodsRepository;

@Service
public class FoodsService {
	
	@Autowired
	FoodsRepository foodsRepository;
	
	public List<Food> findAllFoods(){
		return foodsRepository.findAllFoods();
	}
	
	public Food findFoodById(Long id){
		return foodsRepository.findFoodById(id);
	}
	
	

}
