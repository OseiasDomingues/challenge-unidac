package com.grupodl.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.grupodl.models.Collaborator;
import com.grupodl.models.Food;

public interface FoodsRepository extends Repository<Food, Long> {
	
	@Query(value = "SELECT * FROM TB_Foods", nativeQuery = true)
    List<Collaborator> findAllFoods();	
	
	@Query(value = "SELECT * FROM TB_Foods where id=:id" , nativeQuery = true)
    Collaborator findFoodById(@Param("id") Long id);
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_Foods (name,id_collaborator) values (:name,:id_collaborator)", nativeQuery = true)
    void registerFoods(@Param("name") String name,@Param("id_collaborator")String string);

}
