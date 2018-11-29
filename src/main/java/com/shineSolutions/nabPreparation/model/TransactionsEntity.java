package com.shineSolutions.nabPreparation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transactions")

public class TransactionsEntity {
    @Id
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
