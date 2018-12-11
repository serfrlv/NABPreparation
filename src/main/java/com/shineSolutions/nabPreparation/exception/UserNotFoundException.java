package com.shineSolutions.nabPreparation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such User")
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userId){
        super("Could not find User "+ userId);
    }
}
