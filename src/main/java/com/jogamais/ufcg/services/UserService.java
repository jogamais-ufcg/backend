package com.jogamais.ufcg.services;

import org.modelmapper.ModelMapper;
import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ModelMapper modelMapper;

    public User getById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    //talvez isso esteja errado pois nao sei pegar por toda a entidade de uma vez
    public void delete(User user) throws UserException {
        User u = userRepository.findUser(user);

        if (u == null) {
           throw new UserException();
        }

        userRepository.delete(u);

    }

    public void deleteById(Long id) throws UserException {
        User user = getById(id);
        userRepository.delete(user);
    }

    public List<User> findAll() throws UserException {

        List<User> users = userRepository.findAll().stream().map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            throw new UserException();
        }
        return users;
    }
}
