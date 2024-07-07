package com.mortality.api.domain.population;

public record PopulationRequestDTO(String isoCode, Integer year, Integer value, Integer population_female,
                                   Integer population_male) {
}
