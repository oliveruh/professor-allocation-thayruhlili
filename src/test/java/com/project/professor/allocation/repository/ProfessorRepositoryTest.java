package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository professorRepository;

    @Test
    public void findAll() {
        // Act
        List<Professor> professors = professorRepository.findAll();

        // Print
        professors.forEach(System.out::println);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Professor professor = professorRepository.findById(id).orElse(null);

        // Print
        System.out.println(professor);
    }

    @Test
    public void findByNameContainingIgnoreCase() {
        String name = "Professor";

        List<Professor> professors = professorRepository.findByNameContainingIgnoreCase(name);

        professors.forEach(System.out::println);
    }

    @Test
    public void findByDepartmentId() {
		Department department = new Department();
		department.setId(1L);

		List<Professor> professors = professorRepository.findByDepartment(department);

        professors.forEach(System.out::println);
    }
    
    @Test
    public void save_create() {
    	Department department = new Department();
    	department.setId(1L);
    	
        Professor professor = new Professor();
        professor.setId(null);
        professor.setName("Professor 1");
        professor.setCpf("111.111.111-11");
        professor.setDpt(department);

        professor = professorRepository.save(professor);

        System.out.println(professor);
    }

    @Test
    public void save_update() {
    	Department department = new Department();
    	department.setId(1L);
    	
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Professor 2");
        professor.setCpf("222.222.222-22");
        professor.setDpt(department);

        professor = professorRepository.save(professor);

        System.out.println(professor);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        professorRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        professorRepository.deleteAllInBatch();
    }
}