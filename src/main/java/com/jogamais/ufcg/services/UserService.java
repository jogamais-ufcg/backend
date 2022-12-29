package com.jogamais.ufcg.services;

import com.jogamais.ufcg.dto.UserConfirmationDTO;
import com.jogamais.ufcg.dto.UserEditDTO;
import com.jogamais.ufcg.exceptions.*;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.repositories.UserRepository;
import com.jogamais.ufcg.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public User createWithFiles(User user, MultipartFile fileFront, MultipartFile fileBack) throws UserException, UserMissingEnrollmentException, UserMissingFileBack, UserInvalidCPF, UserInvalidEnrollment, UserInvalidNumberException {
        verifyIfUserExists(user);
        validateUserCreationFields(user, fileBack);

        if (user.getIsStudent()) {
            user.setIsUFCGMember(true);
        }

        // TODO: Passo 5
        // EmailService.sendAccountCreationRequestEmail("adminufcg@gmail.com", user, fileFront, fileBack);
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return create(user);
    }

    private void verifyIfUserExists(User user) throws UserException {
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser != null) {
            throw new UserException();
        }

        foundUser = userRepository.findByCpf(user.getCpf());
        if (foundUser != null) {
            throw new UserException();
        }

        if (user.getIsStudent()) {
            foundUser = userRepository.findByEnrollment(user.getEnrollment());
            if (foundUser != null) {
                throw new UserException();
            }
        }
    }

    private void validateUserCreationFields(User user, MultipartFile fileBack) throws UserMissingEnrollmentException, UserMissingFileBack, UserInvalidEnrollment, UserInvalidCPF, UserInvalidNumberException {
        if (user.getIsStudent()) {
            if (user.getEnrollment() == null) {
                throw new UserMissingEnrollmentException();
            }

            if (!Validations.isOnlyDigits(user.getEnrollment())) {
                throw new UserInvalidEnrollment();
            }
        } else {
            if (fileBack == null) {
                throw new UserMissingFileBack();
            }
        }

        if (!Validations.isCPF(user.getCpf())) {
            throw new UserInvalidCPF();
        }

        if (!Validations.isOnlyDigits(user.getPhoneNumber())) {
            throw new UserInvalidNumberException();
        }
    }

    public void userConfirmation(Long id, UserConfirmationDTO userConfirmationDTO) throws UserException {
        User user = getById(id);
        user.setIsConfirmed(userConfirmationDTO.getIsConfirmed());

        if (!userConfirmationDTO.getIsConfirmed()) {
            deleteById(id);
        } else {
            userRepository.save(user);
        }

        // TODO: Email dizendo se o cadastro foi aceito ou não.
        // EmailService.sendConfirmationUser(user.getEmail(), user);

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

    public User editUser(Long id, UserEditDTO userEditDTO) throws UserException, UserInvalidNumberException, UserInvalidInputException {
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

        return create(user);
    }

    @Override
    public Page<User> search(String searchTerm, int page, int size) {
        return null;
    }
}
