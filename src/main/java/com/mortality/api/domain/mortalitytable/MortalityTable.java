package com.mortality.api.domain.mortalitytable;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    private Integer year;

    @Column(name = "rate_female")
    @JsonProperty("rate_female")
    private BigDecimal rateFemale;

    @Column(name = "rate_male")
    @JsonProperty("rate_male")
    private BigDecimal rateMale;


}
