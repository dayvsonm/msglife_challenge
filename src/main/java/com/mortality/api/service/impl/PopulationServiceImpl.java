package com.mortality.api.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortality.api.domain.country.Country;
import com.mortality.api.domain.population.Population;
import com.mortality.api.domain.population.PopulationRequestDTO;
import com.mortality.api.repositories.PopulationRepository;
import com.mortality.api.service.CountryService;
import com.mortality.api.service.PopulationService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PopulationServiceImpl implements PopulationService {

    private static final String API_URL = "https://run.mocky.io/v3/0040f608-05f7-4635-a113-e52da013b2c0";

    @Autowired
    private PopulationRepository populationRepository;

    @Autowired
    private CountryService countryService;

    @Override
    public void fetchPopulation() {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(API_URL, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        List<Population> populations = new ArrayList<>();

        try {
            populations = objectMapper.readValue(jsonResponse, new TypeReference<List<Population>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Population population : populations) {
            populationRepository.upsertData(
                    population.getCountry().getIsoCode(),
                    population.getYear(),
                    population.getValue(),
                    population.getPopulation_female(),
                    population.getPopulation_male());
        }

    }

    @Override
    public List<Population> getPopulations() {
        try {
            return populationRepository.findAll();
        } catch (NoResultException ex) {
            throw new NoResultException(ex.getMessage());
        }
    }

    @Override
    public Population findPopulationByCode(String code, Integer year) {
        Optional<Population> populationOptional = Optional.ofNullable(populationRepository.findByCountryIsoCodeAndYear(code.toUpperCase(), year));
        return populationOptional.orElseThrow(() -> new NoResultException("População com ISO code '" + code + "' e Ano '" + year + "' não encontrado."));
    }

    @Override
    public Population createEvent(PopulationRequestDTO body) {

        Country country = countryService.getCountryByCode(body.isoCode());
        if (country == null) {
            throw new NoResultException("Country com Iso Code '" + body.isoCode() + "' não encontrado.");
        }

        Population newPopulation = new Population();
        newPopulation.setCountry(country);
        newPopulation.setYear(body.year());
        newPopulation.setValue(body.value());
        newPopulation.setPopulation_female(body.population_female());
        newPopulation.setPopulation_male(body.population_male());

        try {
            populationRepository.save(newPopulation);
        } catch (DataIntegrityViolationException dke) {
            throw new DuplicateKeyException("ISO code '" + body.isoCode() + "' para este Ano '" + body.year() + "'.");
        }


        return newPopulation;
    }
}
