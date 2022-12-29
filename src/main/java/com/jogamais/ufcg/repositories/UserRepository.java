package com.jogamais.ufcg.repositories;


import com.jogamais.ufcg.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByCpf(String cpf);
    User findByEnrollment(String enrollment);
}
