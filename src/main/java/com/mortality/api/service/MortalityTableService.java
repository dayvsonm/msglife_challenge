package com.mortality.api.service;

import com.mortality.api.domain.mortalitytable.MortalityRequestDTO;
import com.mortality.api.domain.mortalitytable.MortalityResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MortalityTableService {
    MortalityResponseDTO create(MortalityRequestDTO mortality);

    List<MortalityResponseDTO> getAllByYear(Integer year);

    MortalityResponseDTO getAllByCountryAndYear(String isoCode, Integer year);

    MortalityResponseDTO upsert(MortalityRequestDTO mortalityRequestDTO);

    ResponseEntity<List<MortalityResponseDTO>> processCSV(MultipartFile file);
}
