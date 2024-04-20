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
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Allocations")
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        super();
        this.allocationService = allocationService;
    }

    @Operation(summary = "Procura todas as alocações.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de alocações."),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allocation>> findAll() {
        List<Allocation> allocations = allocationService.findAll();
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @Operation(summary = "Procura uma alocação a partir de um ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma alocação específica."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhuma alocação encontrada.")
    })
    @GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
        Allocation allocation = allocationService.findById(id);
        if (allocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allocation, HttpStatus.OK);
        }
    }

    @Operation(summary = "Procura alocações pelo ID do curso.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de alocações relacionadas a um determinado curso."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    })
    @GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long crsId) {
        List<Allocation> allocations = allocationService.findByCourse(crsId);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @Operation(summary = "Procura alocações pelo ID do professor.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de alocações relacionadas a um determinado curso."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    })
    @GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long prfId) {
        List<Allocation> allocations = allocationService.findByProfessor(prfId);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @Operation(summary = "Adiciona uma nova alocação.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Retorna nova alocação adicionada."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allocation> save(@RequestBody Allocation allocation) {
        try {
            Allocation alloc = allocationService.save(allocation);
            return new ResponseEntity<>(alloc, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Atualiza as informações de uma alocação a partir de um ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma alocação."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhuma alocação encontrada.")
    })
    @PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allocation> updateById(@PathVariable(name = "allocation_id") Long id,
            @RequestBody Allocation allocation) {
        allocation.setId(id);
        try {
            Allocation alloc = allocationService.update(allocation);
            if (alloc == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(alloc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Deletar uma alocação a partir do ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nenhum conteúdo."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
    })
    @DeleteMapping(path = "/{allocation_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
        allocationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deletar todas as alocações")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nenhum conteúdo.")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        allocationService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
