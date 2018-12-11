package com.shineSolutions.nabPreparation.controller;

import com.shineSolutions.nabPreparation.exception.UserNotFoundException;
import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        List<UserDTO> users = Arrays.asList(u1,u2,u3);
        TransactionDTO t1 = TransactionDTO.builder().originUserId(1l).originUserName("James.Bond")
                .targetUserId(2l).targetUserName("Tom.Hanks").amount(BigDecimal.valueOf(50)).transDate(LocalDate.parse("2018-11-04")).build();
        List<TransactionDTO> trans = Arrays.asList(t1);
        given(this.userService.findAllUsers()).willReturn(users);
        UserDTO newUser = UserDTO.builder().userName("David.Jones").build();
        given(this.userService.addUser(UserDTO.builder().userName("David.Jones").build())).willReturn(newUser);
        given(userService.findTransactionsByUserId(Mockito.anyLong())).willReturn(trans);
    }

    @Test
    public void testGetTransactionByUserSuccessful() throws Exception {
        mockMvc.perform(get("/user/{userId}/transactions", TEST_USER_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("James.Bond")))
                .andExpect(content().string(containsString("Tom.Hanks")));

    }

    @Test
    public void testGetUsersSuccessful() throws Exception {
        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("James.Bond")))
                .andExpect(content().string(containsString("Tom.Hanks")))
                .andExpect(content().string(containsString("Jimmy.White")));

    }

    @Test
    public void testGetUserNotFoundShouldException() throws Exception {
        given(userService.findUserByUserId(6l)).willThrow(new UserNotFoundException("5"));
        mockMvc.perform(get("/user/{userId}",5))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("No such User")));

    }

    @Test
    public void testPostUserSuccessful() throws Exception{
        String jason = "{\"userName\":\"Rooney.Wane\"}";
        mockMvc.perform(post("/user")
                .content(jason)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPutUserSuccessful() throws Exception{
        String jason = "{\"userName\":\"Rooney.Wane\"}";
        mockMvc.perform(put("/user/{userId}","4")
                .content(jason)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserSuccessful() throws Exception{
        mockMvc.perform(delete("/user/{userId}","3")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}