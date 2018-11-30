package com.shineSolutions.nabPreparation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDTO {

    private Long  userId;
    private String userName;
}
