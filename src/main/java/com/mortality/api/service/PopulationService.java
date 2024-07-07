package com.mortality.api.service;

import com.mortality.api.domain.population.Population;
import com.mortality.api.domain.population.PopulationRequestDTO;

import java.util.List;

public interface PopulationService {
    void fetchPopulation();
    List<Population> getPopulations();
    Population findPopulationByCode(String code, Integer year);
    Population createEvent(PopulationRequestDTO body);
    Population fetchPopulationByCodeYear(String code, Integer year);
}
