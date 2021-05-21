package com.example.demo.repositories;

import com.example.demo.models.DeveloperDto;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DeveloperRepository {

    private Environment environment;

    public DeveloperRepository(Environment environment) {
        this.environment = environment;
    }

    public DeveloperDto getDeveloper() {
        DeveloperDto developerDto = new DeveloperDto();

        developerDto.setName(this.environment.getProperty("developer.name"));
        developerDto.setEmail(this.environment.getProperty("developer.email"));
        developerDto.setPhone(this.environment.getProperty("developer.phone"));
        developerDto.setGitHub(this.environment.getProperty("developer.github"));

        return developerDto;
    }

}
