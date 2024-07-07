package com.mortality.api.domain.mortalitytable;

import java.math.BigDecimal;
import java.util.UUID;

public record MortalityResponseDTO(UUID id, String iso_code, Integer year, BigDecimal mortality_rate_female,
                                   BigDecimal mortality_rate_male, Integer total_population, Integer total_female,
                                   Integer total_male) {
}
