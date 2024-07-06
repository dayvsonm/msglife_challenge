package com.mortality.api.domain.population;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private Integer year;
    private Integer value;
    private Integer population_female;
    private Integer population_male;
}
