package com.project.professor.allocation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(example = "11")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(example = "Maria de Oliveira")
	@Column(nullable=false)
	private String name;

	@Schema(example = "00000000000", required = true)
	@Column(nullable=false, unique=true, length=11)
	private String cpf;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", unique=false, nullable=false)
	private Department dpt;
	
	@Schema(example = "22")
	public void setDptId(Long id) {
		Department department = new Department();
		department.setId(id);
		this.setDpt(department);
	}
	
	@Override
	public String toString() {
		return "Professor #" + id + ", Nome: " + name + ", CPF: " + cpf + "]";
	}
	
	
	
	
	
}
