package com.shineSolutions.nabPreparation.service;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
     List<TransactionDTO> findTransactionsByUserId(Long userId);

     List<UserDTO> findAllUsers();

     List<TransactionDTO> findAllTransactions();

     UserDTO addUser(UserDTO userDTO);

     Optional<UserDTO> findUserByUserId(Long userId);

     void deleteByUserId(Long userId);

     void update(Long userId, UserDTO userDtO);
}
