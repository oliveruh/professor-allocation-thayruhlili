package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.AllocationService;

public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        super();
        this.allocationService = allocationService;
    }

}
