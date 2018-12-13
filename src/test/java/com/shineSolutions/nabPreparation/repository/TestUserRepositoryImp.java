package com.shineSolutions.nabPreparation.repository;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.shineSolutions.nabPreparation.exception.UserNotFoundException;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@EnableCircuitBreaker
public class TestUserRepositoryImp {

    @Mock
    protected UserRepository usersRepo;

    @Spy
    @InjectMocks
    private UserRepositoryImp userRepositoryImp;

    @Before
    public void setup(){

    }

    private void resetHystrix() {
        Hystrix.reset();
    }

    public static HystrixCircuitBreaker getCircuitBreaker() {
        return HystrixCircuitBreaker.Factory.getInstance(HystrixCommandKey.Factory.asKey("findAllUsers"));
    }

    private void openCircuitBreakerAfterOneFailingRequest() {

        ConfigurationManager.getConfigInstance().
                setProperty("hystrix.command.findOneById.circuitBreaker.requestVolumeThreshold", 1);
    }

    @Test(expected = UserNotFoundException.class)
    public void findUser_userNotFound_exceptionThrown() {
        when(usersRepo.findById(1L)).thenReturn(Optional.empty());
        userRepositoryImp.findUserByUserId(1L);
    }

    @Ignore
    @Test
    public void circuitBreakerOpenOnException() throws IOException, InterruptedException {

    }


    @Ignore
    @Test
    public void find_all_users_timeout_should_return_empty(){
        when(usersRepo.findAll()).thenThrow(NullPointerException.class);
        assertThat(userRepositoryImp.findAllUsers()).isEmpty();
    }


    private void waitUntilCircuitBreakerOpens() throws InterruptedException {
        Thread.sleep(1000);
    }




}
