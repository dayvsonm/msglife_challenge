package com.mortality.api.repositories;

import com.mortality.api.domain.population.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface PopulationRepository extends JpaRepository<Population, UUID> {

    Population findByCountryIsoCode(String countryIsoCode);


    @Transactional
    @Modifying
    @Query(value = " INSERT INTO population (id, year, value, population_female, population_male, country_code) " +
            "VALUES (gen_random_uuid(), :year, :value, :populationFemale, :populationMale, :countryIsoCode) " +
            "ON CONFLICT (year,country_code) DO UPDATE  " +
            "SET year = EXCLUDED.year, " +
            "value = EXCLUDED.value, " +
            "population_female = EXCLUDED.population_female, " +
            "population_male = EXCLUDED.population_male, " +
            "country_code = EXCLUDED.country_code " +
            "WHERE " +
            "    EXISTS (SELECT 1 FROM public.country WHERE country.iso_code = EXCLUDED.country_code);", nativeQuery = true)
    void upsertData(@Param("countryIsoCode") String countryIsoCode,
                    @Param("year") int year,
                    @Param("value") long value,
                    @Param("populationFemale") long populationFemale,
                    @Param("populationMale") long populationMale);
}
