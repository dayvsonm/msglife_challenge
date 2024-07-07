package com.mortality.api.controller;

import com.mortality.api.domain.country.Country;
import com.mortality.api.domain.population.Population;
import com.mortality.api.domain.population.PopulationRequestDTO;
import com.mortality.api.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/populations")
public class PopulationController {

    @Autowired
    private PopulationService populationService;

    @GetMapping
    public ResponseEntity<List<Population>> getPopulations() {
        List<Population> populations = this.populationService.getPopulations();
        return populations.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(populations);
    }

    @GetMapping("country/{isoCode}/year/{year}")
    public ResponseEntity<Population> getPopulationByCountryIsoCodeAndYear(@PathVariable String isoCode, @PathVariable Integer year) {
        Population population = this.populationService.findPopulationByCode(isoCode, year);
        return population != null ? ResponseEntity.ok(population) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public  ResponseEntity<Population> create(@RequestBody PopulationRequestDTO body) {
        Population population = this.populationService.createEvent(body);
        return population != null ? ResponseEntity.ok(population) : ResponseEntity.noContent().build();
    }
}
