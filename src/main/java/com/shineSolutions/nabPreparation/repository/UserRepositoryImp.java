package com.shineSolutions.nabPreparation.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserRepositoryImp {

    @Autowired
    private UserRepository userRepository;


    @HystrixCommand(fallbackMethod = "fallback_findAllUsers", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<UsersEntity> findAllUsers(){
        return (List<UsersEntity>) userRepository.findAll();
    }


    @HystrixCommand(fallbackMethod = "fallback_addUser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public UsersEntity addUser(UsersEntity usersEntity){
        return userRepository.save(usersEntity);
    }


    @HystrixCommand(fallbackMethod = "fallback_findUserByUserId", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public UsersEntity findUserByUserId(long  userId){
        return userRepository.findUsersEntityByUserId(userId);
    }

    public UsersEntity fallback_findUserByUserId(long userId, Throwable e) {
        log.error(e.getMessage());
        return new UsersEntity();
    }

    public List<UsersEntity> fallback_findAllUsers(Throwable e) {
        log.error(e.getMessage());
        return new ArrayList();
    }

    public UsersEntity fallback_addUser(UsersEntity usersEntity, Throwable e) {
        log.error(e.getMessage());
        return new UsersEntity();
    }
}
