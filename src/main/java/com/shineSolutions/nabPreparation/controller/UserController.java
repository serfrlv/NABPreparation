package com.shineSolutions.nabPreparation.controller;

import com.shineSolutions.nabPreparation.exception.UserNotFoundException;
import com.shineSolutions.nabPreparation.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shineSolutions.nabPreparation.service.IUserService;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value="/user")
    public Iterable getUsers(){
        return userService.findAllUsers();
    }


    @GetMapping(value="/user/{userId}")
    public UserDTO getUserByUserId(@PathVariable String userId){
        Optional<UserDTO> user = userService.findUserByUserId(Long.valueOf(userId));
        if(!user.isPresent()){
            log.info("no user found in controller");
            throw new UserNotFoundException(userId);
        }
        log.info(userId+" has been found!");
        return user.get();
    }

    @PostMapping("/user")
    public UserDTO addUser(@RequestBody UserDTO user) {
         return this.userService.addUser(user);
    }

    @DeleteMapping(value="/user/{userId}")
    public void removeUser(@PathVariable String userId){
        userService.deleteByUserId(Long.valueOf(userId));
    }

    @PutMapping("/user/{userId}")
    public void updateUser(@RequestBody UserDTO userDTO,@PathVariable String userId){
        userService.update(Long.valueOf(userId),userDTO);
    }

    @GetMapping(value="/user/resource/{userId}")
    public HttpEntity<UserDTO> getUserResourceByUserId(@PathVariable String userId){
        UserDTO userDTO =  userService.findUserByUserId(Long.valueOf(userId)).get();
        userDTO.add(linkTo(methodOn(UserController.class).getUserResourceByUserId(userId)).withSelfRel());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value="user/{userId}/transactions")
    public Iterable getTransactionsByUserId(@PathVariable String userId){
        return userService.findTransactionsByUserId(Long.valueOf(userId));
    }










}


