package com.shineSolutions.nabPreparation.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserRepositoryImp {

    @Autowired
    private UserRepository userRepository;

    @HystrixCommand(fallbackMethod = "fallback_addUser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public UsersEntity addUser(UsersEntity usersEntity){
        return userRepository.save(usersEntity);
    }
    private UsersEntity  fallback_addUser(UsersEntity usersEntity, Throwable e) {
        log.error(e.getMessage());
        return new UsersEntity();
    }

    @HystrixCommand(fallbackMethod = "fallback_findAllUsers", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<UsersEntity> findAllUsers(){
        return (List<UsersEntity>) userRepository.findAll();
    }
    @SuppressWarnings("unchecked")
    private List<UsersEntity> fallback_findAllUsers(Throwable e) {
        log.error(e.getMessage());
        return new ArrayList();
    }

    @HystrixCommand(fallbackMethod = "fallback_deleteByUserId", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public void deleteByUserId(long userId){ userRepository.deleteById(userId);}
    private void fallback_deleteByUserId(long userId, Throwable e) {
        log.error(e.getMessage());
    }

    @HystrixCommand(fallbackMethod = "fallback_findUserByUserId", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Optional<UsersEntity> findUserByUserId(long  userId){
        return userRepository.findById(userId);
    }
    private Optional<UsersEntity>  fallback_findUserByUserId(long userId, Throwable e) {
        log.error(e.getMessage());
        return Optional.empty();
    }

    @HystrixCommand(fallbackMethod = "fallback_saveUser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public void saveUser(UsersEntity usersEntity){
        userRepository.save(usersEntity);
    }
    private void fallback_saveUser(UsersEntity usersEntity, Throwable e) {
        log.error(e.getMessage());

    }

}
