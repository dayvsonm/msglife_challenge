package com.mortality.api.service;

import com.mortality.api.domain.country.Country;

import java.util.List;

public interface CountryService {
    List<Country> getCountries();
    Country getCountryByCode(String code);
}
