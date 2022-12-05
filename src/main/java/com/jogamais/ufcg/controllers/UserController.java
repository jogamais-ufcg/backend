package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.exceptions.InternalUserErrorException;
import com.jogamais.ufcg.models.User;
//import com.jogamais.ufcg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/*
@RestController
@RequestMapping(value="/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getById() {

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody User user, UriComponentsBuilder ucBuilder) throws InternalUserErrorException{
        try {
            User addedUser = userService.save(user);
            return new ResponseEntity<User>(addedUser, HttpStatus.OK);
        } catch (InternalUserErrorException e) {
            return ErroProduto.erroProdutoNaoEnconrtrado(idProduto);
        }

    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete() {

    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById() {

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

    }
}
*/
