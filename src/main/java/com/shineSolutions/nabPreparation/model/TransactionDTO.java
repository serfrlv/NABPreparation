package com.shineSolutions.nabPreparation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@Builder
public class TransactionDTO {

    private Long originUserId;
    private String originUserName;
    private Long targetUserId;
    private String targetUserName;
    private double amount;
    private LocalDate transDate;
}
