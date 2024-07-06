package com.mortality.api.controller;


import com.mortality.api.domain.country.Country;
import com.mortality.api.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CountryControllerTest {

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("US", "United States"));
        countries.add(new Country("CA", "Canada"));

        when(countryService.getCountries()).thenReturn(countries);

        ResponseEntity<List<Country>> response = countryController.getCountries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("US", response.getBody().get(0).getIsoCode());
        assertEquals("United States", response.getBody().get(0).getName());
        assertEquals("CA", response.getBody().get(1).getIsoCode());
        assertEquals("Canada", response.getBody().get(1).getName());
    }

    @Test
    public void testGetCountryByIsoCode() {

        String isoCode = "US";
        Country country = new Country("US", "United States");

        when(countryService.getCountryByCode(isoCode)).thenReturn(country);

        ResponseEntity<Country> response = countryController.getCountryByIsoCode(isoCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("US", response.getBody().getIsoCode());
        assertEquals("United States", response.getBody().getName());
    }
}
