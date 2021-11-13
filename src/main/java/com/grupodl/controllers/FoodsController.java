package com.grupodl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupodl.models.Food;
import com.grupodl.services.FoodsService;

@RestController
@RequestMapping("/api")
public class FoodsController {
	
	@Autowired
	FoodsService foodsService;
	
	@GetMapping("/foods")
	public ResponseEntity<List<Food>> findAllFoods(){
		List<Food> foods = foodsService.findAllFoods();
		return ResponseEntity.status(HttpStatus.OK).body(foods);
	}
	
	@GetMapping("/foods/{id}")
	public ResponseEntity<Food> findFoodById(@PathVariable Long id){
		Food food = foodsService.findFoodById(id);
		return ResponseEntity.status(HttpStatus.OK).body(food);
	}

}
