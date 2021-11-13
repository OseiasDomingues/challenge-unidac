package com.grupodl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupodl.models.Collaborator;
import com.grupodl.services.CollaboratorService;

@RestController
@RequestMapping("/api")
public class CollaboratorController {
	
	@Autowired
	CollaboratorService collaboratorService;
	
	@GetMapping("/collaborators")
	public ResponseEntity<List<Collaborator>> findAllCollaborator(){
		List<Collaborator> listCollaborator = collaboratorService.findAllCollaborator();
		return ResponseEntity.status(HttpStatus.OK).body(listCollaborator);
	}
	
	@GetMapping("/collaborators/{cpf}")
	public ResponseEntity<Collaborator> findCollaboratorByCpf(@PathVariable String cpf){
		Collaborator collaborator = collaboratorService.findCollaboratorByCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(collaborator);
	}
	
	@PostMapping("/collaborators")
	public ResponseEntity<Void> insertBreakfast(@RequestBody Collaborator collaborator){
		collaboratorService.insertBreakfast(collaborator);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
