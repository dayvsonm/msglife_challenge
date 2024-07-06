package com.mortality.api;

import com.mortality.api.service.PopulationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	private final PopulationService populationService;

    public ApiApplication(PopulationService populationService) {
        this.populationService = populationService;
    }

    public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner fetchAndPrintData() {
		return args -> {
			populationService.fetchPopulation();
		};
	}

}
