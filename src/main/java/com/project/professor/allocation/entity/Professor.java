package com.project.professor.allocation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false, unique=true,length=11)
	private String cpf;
	
	@ManyToOne(optional = false)
	@JoinColumn(unique=false,nullable=false)
	private Department dpt;
	
	
	@Override
	public String toString() {
		return "Professor #" + id + ", Nome: " + name + ", CPF: " + cpf + "]";
	}
	
	
	
	
	
}
