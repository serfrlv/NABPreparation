package com.shineSolutions.nabPreparation.controller;


import com.shineSolutions.nabPreparation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/transactions")
    public Iterable getAllTransactions(){
        return userService.findAllTransactions();
    }
}
