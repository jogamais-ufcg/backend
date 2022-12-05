package com.jogamais.ufcg.services;

import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IService<User>{

    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) {

        return null;
    }

    public User save(User entity) {

        return entity;
    }

    public void delete(User entity) {

    }

    public void deleteById(Long id) {

    }

    public List<User> findAll() {

        return null;
    }
}


