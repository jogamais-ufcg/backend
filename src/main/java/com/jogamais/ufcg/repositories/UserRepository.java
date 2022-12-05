package com.jogamais.ufcg.repositories;


import com.jogamais.ufcg.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //User findUser(User user);
}
