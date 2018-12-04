package com.shineSolutions.nabPreparation.service;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import com.shineSolutions.nabPreparation.repository.TransactionRepositoryImp;
import com.shineSolutions.nabPreparation.repository.UserRepositoryImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    @Mock
    protected TransactionRepositoryImp transRepo;

    @Mock
    protected UserRepositoryImp usersRepo;

    @Spy
    @InjectMocks
    private UserService userService;

    @Before
    public void setup(){
        ArrayList<UsersEntity> users = new ArrayList();
        UsersEntity userEntity1 = UsersEntity.builder().userId(1l).name("James.Bond").build();
        users.add(userEntity1);
        UsersEntity userEntity2 = UsersEntity.builder().userId(2l).name("Tom.Hanks").build();
        users.add(userEntity2);
        UsersEntity userEntity3 = UsersEntity.builder().userId(3l).name("Jimmy.White").build();
        users.add(userEntity3);
        Mockito.when(usersRepo.findUsers()).thenReturn(users);
        ArrayList<TransactionsEntity> transactionsEntities = new ArrayList();
        ArrayList<TransactionsEntity> transactionsEntities1 = new ArrayList();
        TransactionsEntity transactionsEntity1 = TransactionsEntity.builder()
                .id(1l).userId(1l).userName("James.Bond").amount(BigDecimal.valueOf(50))
                .targetUserId(2l).targetUserName("Tom.Hanks").build();
        transactionsEntities.add(transactionsEntity1);
        transactionsEntities1.add(transactionsEntity1);
        TransactionsEntity transactionsEntity2 = TransactionsEntity.builder()
                .id(2l).userId(1l).userName("James.Bond").amount(BigDecimal.valueOf(20))
                .targetUserId(3l).targetUserName("Jimmy.White").build();
        transactionsEntities.add(transactionsEntity2);
        transactionsEntities1.add(transactionsEntity2);
        TransactionsEntity transactionsEntity3 = TransactionsEntity.builder()
                .id(3l).userId(2l).userName("Tom.Hanks").amount(BigDecimal.valueOf(25))
                .targetUserId(3l).targetUserName("Jimmy.White").build();
        transactionsEntities.add(transactionsEntity3);
        Mockito.when(transRepo.findAllTransactions()).thenReturn(transactionsEntities);
        Mockito.when(transRepo.findAllByUserId(1l)).thenReturn(transactionsEntities1);
    }

    @Test
    public void shouldFGetAllTransByUserId() {
        Collection<TransactionDTO> entities = this.userService.findTransactionsByUserId(1l);
        assertThat(entities.size()).isEqualTo(2);
    }

    @Test
    public void shouldFGetAllUsers() {
        Collection<UserDTO> users = this.userService.findAllUsers();
        assertThat(users.size()).isEqualTo(3);
    }

    @Test
    public void shouldGetAllTransactions(){
        Collection<TransactionDTO> entities = this.userService.findAllTransactions();
        assertThat(entities.size()).isEqualTo(3);
    }
}
