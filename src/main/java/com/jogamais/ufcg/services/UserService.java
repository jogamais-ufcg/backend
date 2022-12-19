package com.jogamais.ufcg.services;

import com.jogamais.ufcg.dto.UserEditDTO;
import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.exceptions.UserInvalidInputException;
import com.jogamais.ufcg.exceptions.UserInvalidNumberException;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IService<User>{

    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(UserException::new);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) throws UserException {
        User user = getById(id);
        userRepository.delete(user);
    }

    public Page<User> findAll(PageRequest page) {
       return userRepository.findAll(PageRequest.of(page.getPageNumber(), 10));
    }


    public void editUser(Long id, UserEditDTO userEditDTO) throws UserException, UserInvalidNumberException, UserInvalidInputException {
        User user = getById(id);

        if (userEditDTO.getName() != null) {
            if (userEditDTO.getName().isEmpty()) {
                throw new UserInvalidInputException();
            }
            user.setName(userEditDTO.getName());
        }

        if (userEditDTO.getPhoneNumber() != null) {
            if (userEditDTO.getPhoneNumber().length() != 11) {
                throw new UserInvalidNumberException();
            }
            user.setPhoneNumber(userEditDTO.getPhoneNumber());
        }

        create(user);

    }
}
