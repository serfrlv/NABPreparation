package com.shineSolutions.nabPreparation.controller;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value="/{userId}/transactions")
    public List<TransactionDTO> getTransactionsByUserId(@PathVariable String userId){
        return userService.findTransactionsByUserId(Long.valueOf(userId));
    }
    @RequestMapping(value="/")
    public List<UserDTO> getUsers(){
        return userService.findAllUsers();
    }
}
