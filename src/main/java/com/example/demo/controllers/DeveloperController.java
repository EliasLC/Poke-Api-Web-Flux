package com.example.demo.controllers;

import com.example.demo.assemblers.DeveloperAssembler;
import com.example.demo.models.DeveloperDto;
import com.example.demo.services.DeveloperService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeveloperController {

    private final DeveloperService developerService;
    private final DeveloperAssembler developerAssembler;

    public DeveloperController(DeveloperService developerService, DeveloperAssembler developerAssembler) {
        this.developerService = developerService;
        this.developerAssembler = developerAssembler;
    }

    @GetMapping("/help")
    public EntityModel<DeveloperDto> getDeveloperInfo() {
        DeveloperDto developerDto = this.developerService.getDeveloper();

        return this.developerAssembler.toModel(developerDto);
    }

}