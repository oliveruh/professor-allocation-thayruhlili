package com.project.professor.allocation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
	
	@Id
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Schema(example = "22")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(example = "Exatas")
	@Column(nullable=false, unique=true)
	private String name;
	
	
	@Override
	public String toString() {
		return "Departmento: #" + id + " " + name;
	}
	
	
	
	
	
}
