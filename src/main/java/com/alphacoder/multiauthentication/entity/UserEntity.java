package com.alphacoder.multiauthentication.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "USER")
@Data
public class UserEntity {
    @Id
    @Column(name= "USERNAME")
    private String username;

    @Column(name= "PASSWORD")
    private String password;
}
