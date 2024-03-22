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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void findAll() {
        // Act
        List<Department> departments = departmentRepository.findAll();

        // Print
        departments.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Department department = departmentRepository.findById(id).orElse(null);

        System.out.println(department);
    }

    @Test
    public void findByNameContainingIgnoreCase() {
        String name = "Department";

        List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);

        departments.forEach(System.out::println);
    }

    @Test
    public void save_create() {
        Department department = new Department();
        department.setId(null);
        department.setName("Department 1");

        department = departmentRepository.save(department);

        System.out.println(department);
    }

    @Test
    public void save_update() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 2");

        department = departmentRepository.save(department);

        System.out.println(department);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        departmentRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        departmentRepository.deleteAllInBatch();
    }
}