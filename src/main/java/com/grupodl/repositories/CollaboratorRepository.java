package com.grupodl.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.grupodl.models.Collaborator;

public interface CollaboratorRepository extends Repository<Collaborator, Long> {
	
	@Query(value = "SELECT * FROM TB_Collaborator", nativeQuery = true)
    List<Collaborator> findAllCollaborator();	
	
	@Query(value = "SELECT * FROM TB_Collaborator where id=:id" , nativeQuery = true)
    Collaborator findCollaboratorById(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM TB_Collaborator where cpf=:cpf" , nativeQuery = true)
    Collaborator findCollaboratorByCPF(@Param("cpf") String cpf);
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_Collaborator (name,cpf) values (:name,:cpf)", nativeQuery = true)
    Integer registerCollaborator(@Param("cpf")String cpf,@Param("name") String name);
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_Collaborator where id=:id" , nativeQuery = true)
    void deleteCollaborator(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_Collaborator SET name=:name WHERE id=:id", nativeQuery = true)
    void updateCollaborator(@Param("id")Long id, @Param("name")String name);
 
	
}
