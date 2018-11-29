package com.shineSolutions.nabPreparation.controller;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserController {

    @Autowired
    private UserService userService;

    @Test
    public void TestGetUsers() throws Exception{
        List<UserDTO> users = userService.findAllUsers();
        Assert.assertEquals(3,users.size());
        Assert.assertEquals("James.Bond",users.get(0).getUserName());
    }
    @Test
    public void TestGetTransactions() throws Exception{
        List<TransactionDTO> transactionDTOS = userService.findTransactionsByUserId(new Long(2));
        Assert.assertEquals(1,transactionDTOS.size());
    }

}
