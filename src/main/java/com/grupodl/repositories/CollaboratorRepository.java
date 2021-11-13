package com.grupodl.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.grupodl.models.Collaborator;

public interface CollaboratorRepository extends Repository<Collaborator, String> {
	
	@Query(value = "SELECT * FROM TB_Collaborator", nativeQuery = true)
    List<Collaborator> findAllCollaborator();	
	
	@Query(value = "SELECT * FROM TB_Collaborator where cpf=:id" , nativeQuery = true)
    Collaborator findCollaboratorById(@Param("id") String id);
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_Collaborator (name,cpf) values (:name,:cpf)", nativeQuery = true)
    void registerCollaborator(@Param("cpf")String cpf,@Param("name") String name);
	
	

}
