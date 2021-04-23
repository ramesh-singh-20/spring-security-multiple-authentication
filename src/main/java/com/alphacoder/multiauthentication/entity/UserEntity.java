package com.alphacoder.multiauthentication.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "USER")
@Data
public class UserEntity {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "USERNAME")
    private String username;

    @Column(name= "PASSWORD")
    private String password;
}
