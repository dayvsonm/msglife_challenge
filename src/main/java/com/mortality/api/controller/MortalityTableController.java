package com.mortality.api.controller;

import com.mortality.api.domain.mortalitytable.MortalityRequestDTO;
import com.mortality.api.domain.mortalitytable.MortalityResponseDTO;
import com.mortality.api.domain.mortalitytable.MortalityTable;

import com.mortality.api.service.MortalityTableService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mortality")
public class MortalityTableController {

    @Autowired
    private MortalityTableService mortalityTableService;

    @PostMapping
    public ResponseEntity<MortalityResponseDTO> create(@RequestBody MortalityRequestDTO mortalityRequestDTO) {
        MortalityResponseDTO mortalityTable = this.mortalityTableService.create(mortalityRequestDTO);
        return mortalityTable != null ? ResponseEntity.ok(mortalityTable) : ResponseEntity.noContent().build();
    }


    @GetMapping("/year/{year}")
    public ResponseEntity<List<MortalityResponseDTO>> getAllByYear(@PathVariable Integer year) {
        List<MortalityResponseDTO> mortalityResponseDTOS = this.mortalityTableService.getAllByYear(year);
        return mortalityResponseDTOS != null ? ResponseEntity.ok(mortalityResponseDTOS) : ResponseEntity.noContent().build();
    }

    @GetMapping("/country/{isoCode}/year/{year}")
    public ResponseEntity<MortalityResponseDTO> getAllByYear(@PathVariable String isoCode, @PathVariable Integer year) {
        MortalityResponseDTO mortalityResponseDTOS = this.mortalityTableService.getAllByCountryAndYear(isoCode, year);
        return mortalityResponseDTOS != null ? ResponseEntity.ok(mortalityResponseDTOS) : ResponseEntity.noContent().build();
    }

}
