package com.mortality.api.repositories;


import com.mortality.api.domain.mortalitytable.MortalityResponseDTO;
import com.mortality.api.domain.mortalitytable.MortalityTable;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MortalityTableRepository extends JpaRepository<MortalityTable, UUID> {


    @Query(value =  "SELECT " +
            "mt.id AS id, " +
            "c.iso_code AS iso_code, " +
            "mt.YEAR AS YEAR, " +
            "mt.rate_female AS mortality_rate_female, " +
            "mt.rate_male AS mortality_rate_male, " +
            "p.value AS total_population, " +
            "p.population_female AS total_female, " +
            "p.population_male AS total_male " +
            "FROM " +
            "mortality_table AS mt " +
            "INNER JOIN " +
            "country AS c ON mt.country_code = c.iso_code " +
            "INNER JOIN " +
            "population AS p ON mt.country_code = p.country_code AND mt.YEAR = p.YEAR  "+
            "WHERE mt.year = :year;", nativeQuery = true)
    public List<Object[]> getAllByYear(  @Param("year")Integer year);

    @Query(value =  "SELECT  " +
            "mt.id AS id, " +
            "c.iso_code AS iso_code, " +
            "mt.YEAR AS YEAR, " +
            "mt.rate_female AS mortality_rate_female, " +
            "mt.rate_male AS mortality_rate_male, " +
            "p.value AS total_population, " +
            "p.population_female AS total_female, " +
            "p.population_male AS total_male " +
            "FROM " +
            "mortality_table AS mt " +
            "INNER JOIN " +
            "country AS c ON mt.country_code = c.iso_code " +
            "INNER JOIN " +
            "population AS p ON mt.country_code = p.country_code AND mt.YEAR = p.YEAR  "+
            "WHERE mt.year = :year and mt.country_code = :iso_code;", nativeQuery = true)
    public Object getAllByCodeAndYear(  @Param("iso_code")String iso_code, @Param("year")Integer year);
}
