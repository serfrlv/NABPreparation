package com.shineSolutions.nabPreparation.repository;


import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends CrudRepository<TransactionsEntity,Long> {

    List<TransactionsEntity> findByUserId(@Param("userId") Long userId);

}
