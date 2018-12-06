package com.shineSolutions.nabPreparation.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long originUserId;
    private String originUserName;
    private Long targetUserId;
    private String targetUserName;
    private BigDecimal amount;
    private LocalDate transDate;
}
