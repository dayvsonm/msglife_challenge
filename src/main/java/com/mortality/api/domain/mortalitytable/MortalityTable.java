package com.mortality.api.domain.mortalitytable;

import com.mortality.api.domain.country.Country;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    private Integer year;

    private BigDecimal mortality_rate_female;
    private BigDecimal mortality_rate_male;


}
