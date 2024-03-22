package com.project.professor.allocation.repository;

import java.sql.Time;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

    @Autowired
    AllocationRepository allocationRepository;

    @Test
    public void findAll() {
        // Act
        List<Allocation> allocations = allocationRepository.findAll();

        // Print
        allocations.forEach(System.out::println);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Allocation allocation = allocationRepository.findById(id).orElse(null);

        // Print
        System.out.println(allocation);
    }

    @Test
    public void findByProfessorId() {
        Professor professor = new Professor();
        professor.setId(1L);

        List<Allocation> allocations = allocationRepository.findByProfessor(professor);

        allocations.forEach(System.out::println);
    }

    @Test
    public void findByCourseId() {
        Course course = new Course();
        course.setId(1L);

        List<Allocation> allocations = allocationRepository.findByCourse(course);

        allocations.forEach(System.out::println);
    }

    @Test
    public void save_create() throws ParseException {
    	Professor professor = new Professor();
    	professor.setId(1L);
    	
    	Course course = new Course();
    	course.setId(1L);
    	
        Allocation allocation = new Allocation();
        allocation.setId(null);
        allocation.setDay(DayOfWeek.SUNDAY);
        allocation.setStart(Time.valueOf(LocalTime.of(19, 00)));
        allocation.setEnd(Time.valueOf(LocalTime.of(20, 00)));
        allocation.setProfessor(professor);
        allocation.setCourse(course);

        allocation = allocationRepository.save(allocation);

        System.out.println(allocation);
    }

    @Test
    public void save_update() throws ParseException {
    	Professor professor = new Professor();
    	professor.setId(1L);
    	
    	Course course = new Course();
    	course.setId(1L);
    	
        Allocation allocation = new Allocation();
        allocation.setId(1L);
        allocation.setDay(DayOfWeek.MONDAY);
        allocation.setStart(Time.valueOf(LocalTime.of(19, 00)));
        allocation.setEnd(Time.valueOf(LocalTime.of(20, 00)));
        allocation.setProfessor(professor);
        allocation.setCourse(course);

        allocation = allocationRepository.save(allocation);

        System.out.println(allocation);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        allocationRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        allocationRepository.deleteAllInBatch();
    }
}