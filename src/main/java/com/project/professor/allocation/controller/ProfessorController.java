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

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professors")
@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        super();
        this.professorService = professorService;
    }

    @Operation(summary = "Procura todos os professores, e retorna a partir de um nome.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de professores."),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Professor> professors = professorService.findAll(name);
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @Operation(summary = "Procura um professor a partir do ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna um professor."),
            @ApiResponse(responseCode = "404", description = "Nenhum professor encontrado.")
    })
    @GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
        Professor professor = professorService.findById(id);
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
    }

    @Operation(summary = "Procura professores pelo ID do departamento.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Retorna uma lista de professores de um determinado departamento."),
    	@ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    })
    @GetMapping(path = "/department/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Professor>> findByDepartment(@PathVariable(name = "department_id") Long dptId) {
        List<Professor> professors = professorService.findByDepartment(dptId);
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @Operation(summary = "Adiciona um novo professor.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Retorna novo professor adicionado."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {
        try {
            Professor prof = professorService.save(professor);
            return new ResponseEntity<>(prof, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Atualiza as informações de um professor a partir de um ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Retorna um professor."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    })
    @PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> updateById(@PathVariable(name = "professor_id") Long id,
            @RequestBody Professor professor) {
        professor.setId(id);
        try {
            Professor prof = professorService.save(professor);
            if (prof == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(prof, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Deletar um professor a partir do ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nenhum conteúdo.")
    })
    @DeleteMapping(path = "/{professor_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
        professorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deletar todos os professores.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nenhum conteúdo.")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        professorService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
