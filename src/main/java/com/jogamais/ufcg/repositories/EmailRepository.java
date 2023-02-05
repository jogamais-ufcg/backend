package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}