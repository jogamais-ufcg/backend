package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByCpf(String cpf);

    User findByEnrollment(String enrollment);
}
