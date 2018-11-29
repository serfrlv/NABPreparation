package com.shineSolutions.nabPreparation.repository;


import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionsEntity,Long> {

    List<TransactionsEntity> findByUserId(@Param("userId") Long userId);

}
