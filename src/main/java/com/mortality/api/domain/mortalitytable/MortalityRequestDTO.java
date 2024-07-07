package com.mortality.api.domain.mortalitytable;


import java.math.BigDecimal;

public record MortalityRequestDTO(String isoCode, Integer year, BigDecimal rateFemale, BigDecimal rateMale) {
}
