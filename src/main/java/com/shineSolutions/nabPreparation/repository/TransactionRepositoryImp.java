package com.shineSolutions.nabPreparation.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TransactionRepositoryImp {

    @Autowired
    private TransactionRepository transactionRepository;

    @HystrixCommand(fallbackMethod = "fallback_findAllByUserId", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<TransactionsEntity> findAllByUserId(long userId){
        return transactionRepository.findByUserId(userId);
    }

    @HystrixCommand(fallbackMethod = "fallback_findAllTransactions", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<TransactionsEntity> findAllTransactions(){
        return (List<TransactionsEntity>) transactionRepository.findAll();
    }

    public List<TransactionsEntity> fallback_findAllTransactions() {
        log.error("finding all transactions has a time out exception!");
        return new ArrayList<>();
    }

    public List<TransactionsEntity> fallback_findAllByUserId(long userId) {
        log.error("finding all transactions by UserId has a time out exception!");
        return new ArrayList<>();
    }

}
