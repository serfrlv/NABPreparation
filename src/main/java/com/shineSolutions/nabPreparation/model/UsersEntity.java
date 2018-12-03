package com.shineSolutions.nabPreparation.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long userId;

    @Column(name="name")
    private String name;
}
