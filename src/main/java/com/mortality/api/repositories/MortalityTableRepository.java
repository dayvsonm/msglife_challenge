package com.mortality.api.repositories;


import com.mortality.api.domain.mortalitytable.MortalityTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MortalityTableRepository extends JpaRepository<MortalityTable, UUID> {
}
