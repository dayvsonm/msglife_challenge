package com.mortality.api.service;

import com.mortality.api.domain.country.Country;
import com.mortality.api.repositories.CountryRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        try {
            return countryRepository.findAll();
        }catch (NoResultException ex){
            throw new NoResultException(ex.getMessage());
        }

    }
}
