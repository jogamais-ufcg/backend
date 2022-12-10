package com.jogamais.ufcg.services;

import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IService<User>{

    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    //talvez isso esteja errado pois nao sei pegar por toda a entidade de uma vez
    /*public void delete(User user) throws UserException {
        User u = userRepository.findUser(user);

        if (u == null) {
           throw new UserException();
        }

        userRepository.delete(u);

    }*/

    public void deleteById(Long id) throws UserException {
        User user = getById(id);
        userRepository.delete(user);
    }

    public List<User> findAll() {

        List<User> users = userRepository.findAll();
        return users;
    }
}
