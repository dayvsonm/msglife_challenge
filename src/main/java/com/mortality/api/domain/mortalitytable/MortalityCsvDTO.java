package com.mortality.api.domain.mortalitytable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.opencsv.bean.CsvBindByName;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortalityCsvDTO {
    @CsvBindByName(column = "isoCode")
    private String isoCode;

    @CsvBindByName(column = "year")
    private Integer year;

    @CsvBindByName(column = "rate_female")
    private BigDecimal rateFemale;

    @CsvBindByName(column = "rate_male")
    private BigDecimal rateMale;
}
