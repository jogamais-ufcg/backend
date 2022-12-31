package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}
