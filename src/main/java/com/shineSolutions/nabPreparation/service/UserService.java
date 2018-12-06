package com.shineSolutions.nabPreparation.service;

import com.shineSolutions.nabPreparation.model.TransactionDTO;
import com.shineSolutions.nabPreparation.model.TransactionsEntity;
import com.shineSolutions.nabPreparation.model.UserDTO;
import com.shineSolutions.nabPreparation.model.UsersEntity;
import com.shineSolutions.nabPreparation.repository.TransactionRepositoryImp;
import com.shineSolutions.nabPreparation.repository.UserRepositoryImp;
import org.springframework.stereotype.Service;

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
     * Takes a list of {@link TransactionDTO} by userId
     * @param userId
     * @return
     */
    @Override
    public List<TransactionDTO> findTransactionsByUserId(Long userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream().map(transactionsEntity -> convertTransactionToDto(transactionsEntity))
                .collect(Collectors.toList());
    }

    /**
     * Takes a list of {@link UserDTO}
     * @param
     * @return
     */
    @Override
    public List<UserDTO> findAllUsers() {
        List<UsersEntity> usersEntities = userRepository.findAllUsers();
        return usersEntities.stream().map(usersEntity -> convertUserEntityToDto(usersEntity))
                .collect(Collectors.toList());
    }

    /**
     * Takes a list of {@link TransactionDTO}
     * @param
     * @return
     */
    @Override
    public List<TransactionDTO> findAllTransactions() {
        return transactionRepository.findAllTransactions()
                .stream().map(transactionsEntity -> convertTransactionToDto(transactionsEntity))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO addUser(UserDTO user) {
         return convertUserEntityToDto(userRepository.addUser(convertUserDtotoEntity(user)));
    }

    @Override
    public UserDTO findUserByUserId(long userId) {
        return convertUserEntityToDto(userRepository.findUserByUserId(userId));
    }

    private UserDTO convertUserEntityToDto(UsersEntity usersEntity) {
        return UserDTO.builder()
                .userId(usersEntity.getUserId())
                .userName(usersEntity.getName())
                .build();
    }

    private TransactionDTO convertTransactionToDto(TransactionsEntity transactionsEntity){
        return TransactionDTO.builder()
                .originUserId(transactionsEntity.getUserId())
                .originUserName(transactionsEntity.getUserName())
                .amount(transactionsEntity.getAmount())
                .targetUserId(transactionsEntity.getTargetUserId())
                .targetUserName(transactionsEntity.getTargetUserName())
                .transDate(transactionsEntity.getDate())
                .build();
    }

    private UsersEntity convertUserDtotoEntity(UserDTO userDTO){
        return UsersEntity.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getUserName())
                .build();
    }

}
