package com.shineSolutions.nabPreparation.model;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO  extends ResourceSupport {

    private Long  userId;
    private String userName;
}
