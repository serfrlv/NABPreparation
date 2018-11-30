package com.shineSolutions.nabPreparation.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRepositoryImp {

    @Autowired
    private TransactionRepository transactionRepository;

    @HystrixCommand(fallbackMethod = "reliable")
    public List<TransactionsEntity> findAllByUserId(long userId){
        return transactionRepository.findByUserId(userId);
    }

    public List<TransactionsEntity> reliable(long userId) {
        return null;
    }

}
