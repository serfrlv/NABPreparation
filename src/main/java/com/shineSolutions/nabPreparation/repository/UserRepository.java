package com.shineSolutions.nabPreparation.repository;

import com.shineSolutions.nabPreparation.model.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsersEntity, Long> {

    UsersEntity findUsersEntityByUserId(@Param("userId") Long userId);
}
