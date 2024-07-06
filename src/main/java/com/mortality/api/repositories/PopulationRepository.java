package com.mortality.api.repositories;

import com.mortality.api.domain.population.Population;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PopulationRepository extends JpaRepository<Population, UUID> {
}
