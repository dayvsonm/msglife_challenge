package com.mortality.api.service.impl;

import com.mortality.api.domain.country.Country;
import com.mortality.api.domain.mortalitytable.MortalityRequestDTO;
import com.mortality.api.domain.mortalitytable.MortalityResponseDTO;
import com.mortality.api.domain.mortalitytable.MortalityTable;
import com.mortality.api.domain.population.Population;
import com.mortality.api.repositories.MortalityTableRepository;
import com.mortality.api.service.CountryService;
import com.mortality.api.service.MortalityTableService;
import com.mortality.api.service.PopulationService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MortalityTableServiceImpl implements MortalityTableService {

    @Autowired
    private CountryService countryService;

    @Autowired
    private PopulationService populationService;

    @Autowired
    private MortalityTableRepository mortalityTableRepository;

    @Override
    public MortalityResponseDTO create(MortalityRequestDTO mortality) {

        Country country = countryService.getCountryByCode(mortality.isoCode().toUpperCase());
        if (country == null) {
            throw new NoResultException("Country com Iso Code '" + mortality.isoCode() + "' não encontrado.");
        }

        MortalityTable mortalityTable = new MortalityTable();
        mortalityTable.setCountry(country);
        mortalityTable.setRateFemale(mortality.rateFemale());
        mortalityTable.setRateMale(mortality.rateMale());
        mortalityTable.setYear(mortality.year());

        try {
            mortalityTableRepository.save(mortalityTable);
        } catch (DataIntegrityViolationException dke) {
            throw new DuplicateKeyException("ISO code '" + mortality.isoCode() + "' para este Ano '" + mortality.year() + "'.");
        }


        Population population = populationService.fetchPopulationByCodeYear(mortality.isoCode().toUpperCase(), mortality.year());


        MortalityResponseDTO mortalityResponseDTO = new MortalityResponseDTO(
                mortalityTable.getId(), mortalityTable.getCountry().getIsoCode(), mortalityTable.getYear(),
                mortalityTable.getRateFemale(), mortalityTable.getRateMale(), population.getValue(),
                population.getPopulation_female(), population.getPopulation_male());

        return mortalityResponseDTO;
    }

    @Override
    public List<MortalityResponseDTO> getAllByYear(Integer year) {
        List<Object[]> results  = mortalityTableRepository.getAllByYear(year);

        if (results == null) {
            throw new NoResultException("Tabelas de Mortalidade com Ano '" + year + "' não encontrado.");
        }

        return results.stream().map(result -> new MortalityResponseDTO(
                (UUID) result[0],
                (String) result[1],
                (Integer) result[2],
                (BigDecimal) result[3],
                (BigDecimal)result[4],
                (Integer)result[5],
                (Integer)result[6],
                (Integer) result[7]

        )).collect(Collectors.toList());
    }

    @Override
    public MortalityResponseDTO getAllByCountryAndYear(String isoCode, Integer year) {
        Object result  =  mortalityTableRepository.getAllByCodeAndYear(isoCode.toUpperCase(),year);

        if (result == null) {
            throw new NoResultException("Tabela de Mortalidade com code '"+ isoCode +"' e Ano '" + year + "' não encontrado.");
        }

        Object[] resultArray = (Object[]) result;

        return new MortalityResponseDTO(
                (UUID) resultArray[0],
                (String) resultArray[1],
                (Integer) resultArray[2],
                (BigDecimal) resultArray[3],
                (BigDecimal)resultArray[4],
                (Integer)resultArray[5],
                (Integer)resultArray[6],
                (Integer) resultArray[7]);
    }

    @Override
    public MortalityResponseDTO upsert(MortalityRequestDTO mortalityRequestDTO) {

        MortalityTable  mortalityTable = mortalityTableRepository.findByCountryIsoCodeAndYear
                (mortalityRequestDTO.isoCode().toUpperCase(), mortalityRequestDTO.year());

        if (mortalityTable == null) {
            throw new NoResultException("Tabela de Mortalidade com code '"+ mortalityRequestDTO.isoCode().toUpperCase()
                    +"' e Ano '" +  mortalityRequestDTO.year() + "' não encontrado.");
        }

        mortalityTable.setRateMale(mortalityRequestDTO.rateMale());
        mortalityTable.setRateFemale(mortalityRequestDTO.rateFemale());

        mortalityTableRepository.save(mortalityTable);

        return this.getAllByCountryAndYear(mortalityRequestDTO.isoCode().toUpperCase(), mortalityRequestDTO.year());
    }
}

