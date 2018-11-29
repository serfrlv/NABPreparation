package com.shineSolutions.nabPreparation.service;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import com.shineSolutions.nabPreparation.repository.TransactionRepositoryImp;
import com.shineSolutions.nabPreparation.repository.UserRepositoryImp;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple implementation of {@link IUserService}
 */
@Service
public class UserService implements IUserService{

    private TransactionRepositoryImp transactionRepository;
    private UserRepositoryImp userRepository;



    public UserService(UserRepositoryImp userRepository,TransactionRepositoryImp transactionRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Takes a list of {@link TransactionsEntity} by userId
     * @param userId
     * @return
     */
    @Override
    public List<TransactionDTO> findTransactionsByUserId(Long userId) {
        List<TransactionsEntity> transactionsEntities =  transactionRepository.findAllByUserId(userId);
        return transactionsEntities.stream().map(transactionsEntity -> convertTransactionToDto(transactionsEntity))
        .collect(Collectors.toList());
    }

    /**
     * Takes a list of {@link UsersEntity}
     * @param
     * @return
     */
    @Override
    public List<UserDTO> findAllUsers() {
        List<UsersEntity> usersEntities = userRepository.findUsers();
        return usersEntities.stream().map(usersEntity -> convertUserEntityToDto(usersEntity))
                .collect(Collectors.toList());
    }



    private UserDTO convertUserEntityToDto(UsersEntity usersEntity) {
        UserDTO userDTO =new UserDTO();
        userDTO.setUserName(usersEntity.getName());
        userDTO.setUserId(usersEntity.getUserId());
        return userDTO;
    }

    private TransactionDTO convertTransactionToDto(TransactionsEntity transactionsEntity){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transactionsEntity.getAmount());
        transactionDTO.setOriginUserId(transactionsEntity.getUserId());
        transactionDTO.setOriginUserName(transactionsEntity.getUserName());
        transactionDTO.setTargetUserId(transactionsEntity.getTargetUserId());
        transactionDTO.setTargetUserName(transactionsEntity.getTargetUserName());
        transactionDTO.setTransDate(Date.from(transactionsEntity.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        return transactionDTO;
    }

}
