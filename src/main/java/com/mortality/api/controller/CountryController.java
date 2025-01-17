package com.mortality.api.controller;

import com.mortality.api.domain.country.Country;
import com.mortality.api.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryService.getCountries();
        return countries.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(countries);
    }

    @GetMapping("/{isoCode}")
    public ResponseEntity<Country> getCountryByIsoCode(@PathVariable String isoCode) {
        Country country = countryService.getCountryByCode(isoCode);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }
}
