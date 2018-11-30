package com.shineSolutions.nabPreparation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transactions")
@Getter
@Setter
@ToString
public class TransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="userId")
    private long userId;

    @Column(name="userName")
    private String userName;

    @Column(name="amount")
    private Double amount;

    @Column(name="targetUserId")
    private Long targetUserId;

    @Column(name="targetUserName")
    private String targetUserName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
