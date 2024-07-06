package com.mortality.api.service.impl;

import com.mortality.api.domain.country.Country;
import com.mortality.api.repositories.CountryRepository;
import com.mortality.api.service.CountryService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        try {
            return countryRepository.findAll();
        }catch (NoResultException ex){
            throw new NoResultException(ex.getMessage());
        }
    }

    @Override
    public Country getCountryByCode(String code) {
        Optional<Country> countryOptional = countryRepository.findById(code.toUpperCase());
        return countryOptional.orElseThrow(() -> new NoResultException("País com ISO code '" + code + "' não encontrado."));
    }
}
