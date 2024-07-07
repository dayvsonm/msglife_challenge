package com.mortality.api.service;

import com.mortality.api.domain.mortalitytable.MortalityRequestDTO;
import com.mortality.api.domain.mortalitytable.MortalityResponseDTO;
import com.mortality.api.domain.mortalitytable.MortalityTable;

import java.util.List;

public interface MortalityTableService {
    MortalityResponseDTO create(MortalityRequestDTO mortality);

    List<MortalityResponseDTO> getAllByYear(Integer year);

    MortalityResponseDTO getAllByCountryAndYear(String isoCode, Integer year);
}
