package com.shineSolutions.nabPreparation.service;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.UserDTO;

import java.util.List;

public interface IUserService {
     List<TransactionDTO> findTransactionsByUserId(Long userId);

     List<UserDTO> findAllUsers();

     List<TransactionDTO> findAllTransactions();
}
