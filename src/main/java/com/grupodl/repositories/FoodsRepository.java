package com.grupodl.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.grupodl.models.Food;

public interface FoodsRepository extends Repository<Food, Long> {
	
	@Query(value = "SELECT * FROM TB_Foods", nativeQuery = true)
    List<Food> findAllFoods();	
	
	@Query(value = "SELECT * FROM TB_Foods where id=:id" , nativeQuery = true)
    Food findFoodById(@Param("id") Long id);
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_Foods (name,id_collaborator) values (:name,:id_collaborator)", nativeQuery = true)
    Integer registerFoods(@Param("name") String name,@Param("id_collaborator")Long id_collaborator);
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_Foods where id=:id" , nativeQuery = true)
    void deleteFood(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_Foods SET name=:name WHERE id=:id", nativeQuery = true)
    void updateFood(@Param("id") Long id, @Param("name") String name);

}
