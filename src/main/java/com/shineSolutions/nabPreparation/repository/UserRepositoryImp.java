package com.shineSolutions.nabPreparation.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryImp {
    @Autowired
    private UserRepository userRepository;

    @HystrixCommand(fallbackMethod = "reliable")
    public List<UsersEntity> findUsers(){
        return (List<UsersEntity>) userRepository.findAll();
    }

    public List<UsersEntity> reliable() {
        return null;
    }

}
