package com.shineSolutions.nabPreparation.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long  userId;
    private String userName;
}
