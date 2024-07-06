package com.mortality.api.repositories;

import com.mortality.api.domain.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}
