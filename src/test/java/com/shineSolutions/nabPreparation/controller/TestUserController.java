package com.shineSolutions.nabPreparation.controller;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class TestUserController {

    private static final Long TEST_USER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void init() {
        UserDTO u1 = UserDTO.builder().userName("James.Bond").userId(1l).build();
        UserDTO u2 = UserDTO.builder().userName("Tom.Hanks").userId(2l).build();
        UserDTO u3 = UserDTO.builder().userName("Jimmy.White").userId(3l).build();
        List<UserDTO> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        TransactionDTO t1 = TransactionDTO.builder()
                .originUserId(1l).originUserName("James.Bond")
                .targetUserId(2l).targetUserName("Tom.Hanks")
                .amount(BigDecimal.valueOf(50)).transDate(LocalDate.parse("2018-11-04")).build();
        List<TransactionDTO> trans = new ArrayList<>();
        trans.add(t1);
        given(this.userService.findTransactionsByUserId(TEST_USER_ID)).willReturn(trans);
        given(this.userService.findAllUsers()).willReturn(users);
    }

    @Test
    public void testGetUsers(){
        List<UserDTO> users = userService.findAllUsers();
        Assert.assertEquals(3,users.size());
        Assert.assertEquals("James.Bond",users.get(0).getUserName());
    }
    @Test
    public void testGetTransactions(){
        List<TransactionDTO> trans = userService.findTransactionsByUserId(new Long(1));
        Assert.assertEquals(1,trans.size());
        Assert.assertEquals(BigDecimal.valueOf(50),trans.get(0).getAmount());
    }

    @Test
    public void testGetTransactionByUserSuccessful() throws Exception {
        mockMvc.perform(get("/user/{userId}/transactions", TEST_USER_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsersSuccessful() throws Exception {
        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk());
    }
}
