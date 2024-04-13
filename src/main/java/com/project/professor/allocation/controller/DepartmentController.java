package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Departments")
@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super();
        this.departmentService = departmentService;
    }
    
    @Operation(description = "Procura todos os departamentos e retorna a partir de um nome.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retorna uma lista de departamentos")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name) {
    	List<Department> departments = departmentService.findAll(name);
    	return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    
    @Operation(description = "Procura um departamento a partir de um id.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Retorna um departamento"),
    	@ApiResponse(responseCode = "404", description = "Departamento não encontrado"),
    	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    @GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
    	try {
    		Department department = departmentService.findById(id);
    		if (department == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		} else {
    			return new ResponseEntity<>(department, HttpStatus.OK);
    		}    		
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @Operation(description = "Adiciona um novo departamento")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Retorna o novo departamento adicionado."),
    	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> save(@RequestBody Department department) {
    	try {
    		Department departmentSaved = departmentService.save(department);
    		return new ResponseEntity<>(departmentSaved, HttpStatus.CREATED);    		
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @Operation(description = "Atualiza as informações de um departamento a partir de um ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Retorna um departamento atualizado."),
    	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Departamento não encontrado.")
    })
    @PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id, @RequestBody Department department) {
    	try {
    		department.setId(id);
    		Department departmentUpdated = departmentService.update(department);
    		if (departmentUpdated == null) {
    			return new ResponseEntity<>(departmentUpdated, HttpStatus.NOT_FOUND);
    		} else {
    			return new ResponseEntity<>(departmentUpdated, HttpStatus.OK);
    		}
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @Operation(description = "Deletar um departamento a partir de um ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "Nenhum conteúdo"),
    	@ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping(path = "/{department_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
    	try {
    		departmentService.deleteById(id);
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @Operation(description = "Deleta todos os departamentos.")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "Nenhum conteúdo")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
    	departmentService.deleteAll();
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
