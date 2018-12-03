package com.shineSolutions.nabPreparation.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionRepositoryImp {

    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImp.class);

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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (List<TransactionsEntity>) transactionRepository.findAll();
    }

    public List<TransactionsEntity> fallback_findAllTransactions() {
        logger.error("finding all transactions has a time out exception!");
        return new ArrayList<>();
    }

    public List<TransactionsEntity> fallback_findAllByUserId(long userId) {
        logger.error("finding all transactions by UserId has a time out exception!");
        return new ArrayList<>();
    }

}
