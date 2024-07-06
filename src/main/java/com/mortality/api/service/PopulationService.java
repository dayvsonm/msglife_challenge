package com.mortality.api.service;

import com.mortality.api.domain.population.Population;

import java.util.List;

public interface PopulationService {
    void fetchPopulation();
    List<Population> getPopulation();
    Population findPopulationByCode(String code);
}
