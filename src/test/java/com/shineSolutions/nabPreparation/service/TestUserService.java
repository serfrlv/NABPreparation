package com.shineSolutions.nabPreparation.service;

import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import com.shineSolutions.nabPreparation.repository.TransactionRepositoryImp;
import com.shineSolutions.nabPreparation.repository.UserRepositoryImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TestUserService {

    @Autowired
    protected TransactionRepositoryImp transRepo;

    @Autowired
    protected UserRepositoryImp usersRepo;

    @Test
    public void shouldFGetAllTransByUserId() {
        Collection<TransactionsEntity> entities = this.transRepo.findAllByUserId(1l);
        assertThat(entities.size()).isEqualTo(2);
    }

    @Test
    public void shouldFGetAllUsers() {
        Collection<UsersEntity> users = this.usersRepo.findUsers();
        assertThat(users.size()).isEqualTo(3);
    }

    @Test
    public void shouldGetAllTransactions(){
        Collection<TransactionsEntity> entities = this.transRepo.findAllTransactions();
        assertThat(entities.size()).isEqualTo(3);
    }
}
