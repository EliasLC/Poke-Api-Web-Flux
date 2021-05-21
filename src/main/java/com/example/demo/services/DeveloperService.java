package com.example.demo.services;


import com.example.demo.models.DeveloperDto;
import com.example.demo.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public DeveloperDto getDeveloper() {
        return this.developerRepository.getDeveloper();
    }

}
