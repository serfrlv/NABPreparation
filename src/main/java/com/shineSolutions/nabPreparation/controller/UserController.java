package com.shineSolutions.nabPreparation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shineSolutions.nabPreparation.service.IUserService;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value="user/{userId}/transactions")
    public Iterable getTransactionsByUserId(@PathVariable String userId){
        return userService.findTransactionsByUserId(Long.valueOf(userId));
    }

    @RequestMapping(value="/user")
    public Iterable getUsers(){
        return userService.findAllUsers();
    }
}
