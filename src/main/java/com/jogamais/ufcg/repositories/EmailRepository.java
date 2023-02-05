package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}