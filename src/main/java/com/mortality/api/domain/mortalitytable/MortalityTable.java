package com.mortality.api.domain.mortalitytable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "mortality_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortalityTable {

    @Id
    @GeneratedValue
    private UUID ui;

    private Integer year;
    private String country_code;
    private BigDecimal mortality_rate_female;
    private BigDecimal mortality_rate_male;


}
