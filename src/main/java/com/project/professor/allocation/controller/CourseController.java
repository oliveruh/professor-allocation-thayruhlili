package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.CourseService;

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        super();
        this.courseService = courseService;
    }
    
}
