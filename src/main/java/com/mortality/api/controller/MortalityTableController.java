package com.mortality.api.controller;

import com.mortality.api.domain.mortalitytable.MortalityRequestDTO;
import com.mortality.api.domain.mortalitytable.MortalityResponseDTO;

import com.mortality.api.service.MortalityTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping()
    public ResponseEntity<MortalityResponseDTO> upsert(@RequestBody MortalityRequestDTO mortalityRequestDTO) {
        MortalityResponseDTO mortalityTable = this.mortalityTableService.upsert(mortalityRequestDTO);
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

    @PostMapping(value ="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<MortalityResponseDTO>> upload(@RequestParam("file") MultipartFile file) {
        if (!isCsvFile(file)) {
            throw new RuntimeException("O arquivo fornecido não é um arquivo CSV válido.");
        }
        return  this.mortalityTableService.processCSV(file);
    }

    private boolean isCsvFile(MultipartFile file) {
        // Verifica a extensão do arquivo
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return "csv".equalsIgnoreCase(extension);
    }

}
