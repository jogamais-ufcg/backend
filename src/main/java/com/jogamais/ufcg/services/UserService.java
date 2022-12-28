package com.jogamais.ufcg.services;

import com.jogamais.ufcg.dto.UserEditDTO;
import com.jogamais.ufcg.exceptions.*;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService implements IService<User>{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(UserException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    public User createWithFiles(User user, MultipartFile fileFront, MultipartFile fileBack) throws UserException, UserMissingEnrollmentException, UserMissingFileBack {
//        Optional<User> foundUser = userRepository.findById(user.getId());
//        if (foundUser.isPresent()) {
//            throw new UserException();
//        }

        validateUserCreationFields(user, fileBack);

        // EmailService.sendRequestEmail("adminufcg@gmail.com", user, fileFront, fileBack);
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return create(user);
    }

    private void validateUserCreationFields(User user, MultipartFile fileBack) throws UserMissingEnrollmentException, UserMissingFileBack {
        if (user.getIsStudent()) {
            if (user.getEnrollment() == null) {
                throw new UserMissingEnrollmentException();
            }
        } else {
            if (fileBack == null) {
                throw new UserMissingFileBack();
            }
        }
    }

    @Override
    public void deleteById(Long id) throws UserException {
        User user = getById(id);
        userRepository.delete(user);
    }

    @Override
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

    @Override
    public Page<User> search(String searchTerm, int page, int size) {
        return null;
    }
}
