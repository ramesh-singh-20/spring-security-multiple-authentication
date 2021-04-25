package com.alphacoder.multiauthentication.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "USER_TOKEN")
@Data
public class UserTokenEntity {
    @Id
    @Column(name= "USERNAME")
    private String username;

    @Column(name= "TOKEN")
    private String token;
}
