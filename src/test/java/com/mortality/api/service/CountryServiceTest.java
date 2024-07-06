package com.mortality.api.service;

import com.mortality.api.domain.country.Country;
import com.mortality.api.repositories.CountryRepository;
import com.mortality.api.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeGetAllCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("PT", "Portugal"));
        countries.add(new Country("BR", "Brazil"));

        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> result = countryService.getCountries();

        // Assert
        assertEquals(2, result.size());
        assertEquals("PT", result.get(0).getIsoCode());
        assertEquals("Portugal", result.get(0).getName());
        assertEquals("BR", result.get(1).getIsoCode());
        assertEquals("Brazil", result.get(1).getName());
    }

    @Test
    public void testGetCountryByIsoCode() {
        String isoCode = "PT";
        Country country = new Country("PT", "Portugal");

        when(countryRepository.findById(isoCode)).thenReturn(Optional.of(country));

        Country result = countryService.getCountryByCode(isoCode);
        assertEquals("PT", result.getIsoCode());
        assertEquals("Portugal", result.getName());

    }
}
