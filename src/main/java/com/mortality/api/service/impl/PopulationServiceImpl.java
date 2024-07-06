package com.mortality.api.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortality.api.domain.population.Population;
import com.mortality.api.repositories.PopulationRepository;
import com.mortality.api.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationServiceImpl implements PopulationService {

    private static final String API_URL = "https://run.mocky.io/v3/0040f608-05f7-4635-a113-e52da013b2c0";

    @Autowired
    private PopulationRepository populationRepository;

    @Override
    public void fetchPopulation() {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(API_URL, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        List<Population> populations = new ArrayList<>();

        try {
            populations = objectMapper.readValue(jsonResponse, new TypeReference<List<Population>>() {});
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
    public List<Population> getPopulation() {
        return List.of();
    }

    @Override
    public Population findPopulationByCode(String code) {
        return populationRepository.findByCountryIsoCode(code);
    }
}
