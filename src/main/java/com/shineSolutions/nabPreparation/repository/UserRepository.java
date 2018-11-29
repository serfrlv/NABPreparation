package com.shineSolutions.nabPreparation.repository;

import com.shineSolutions.nabPreparation.model.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UsersEntity, Long> {

}
