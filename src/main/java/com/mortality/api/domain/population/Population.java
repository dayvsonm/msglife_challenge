package com.mortality.api.domain.population;

import com.mortality.api.domain.country.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "population")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Population {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    private Integer year;
    private Integer value;
    private Integer population_female;
    private Integer population_male;
}
