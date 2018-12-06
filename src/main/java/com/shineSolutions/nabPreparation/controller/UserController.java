package com.shineSolutions.nabPreparation.controller;

import com.shineSolutions.nabPreparation.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;
import com.shineSolutions.nabPreparation.service.IUserService;

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

    @GetMapping(value="user/{userId}/transactions")
    public Iterable getTransactionsByUserId(@PathVariable String userId){
        return userService.findTransactionsByUserId(Long.valueOf(userId));
    }

    @PostMapping("/user/new")
    public UserDTO addUser(@RequestBody UserDTO user) {
         return this.userService.addUser(user);
    }

    @GetMapping(value="/user/{userId}")
    public Resource<UserDTO> getUserByUserId(@PathVariable String userId){
        UserDTO user =  userService.findUserByUserId(Long.valueOf(userId));
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).getUserByUserId(userId)).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
    }

}


