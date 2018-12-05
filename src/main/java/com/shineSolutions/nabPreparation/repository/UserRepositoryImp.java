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

    @HystrixCommand(fallbackMethod = "fallback_findAllByUser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<UsersEntity> findUsers(){
        return (List<UsersEntity>) userRepository.findAll();
    }

    public List<UsersEntity> fallback_findAllByUser() {
        log.error("finding all transactions has a time out exception!");
        return new ArrayList();
    }

}
