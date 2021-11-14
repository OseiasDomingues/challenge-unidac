package com.grupodl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@Entity
@Table(name="TB_Collaborator") 
public class Collaborator implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotBlank
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
	private String cpf;
	@NotBlank
	private String name;
	
	@OneToMany(mappedBy = "collaborator")
	private List<Food> foods = new ArrayList<>();
	
	
		

	public Collaborator() {
	}

	public Collaborator(String cpf, String name) {
		this.cpf = cpf;
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Food> getFoods() {
		return foods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collaborator other = (Collaborator) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Collaborator [cpf=" + cpf + ", name=" + name + ", foods=" + foods + "]";
	}
	
	
	
	
	
	

}
