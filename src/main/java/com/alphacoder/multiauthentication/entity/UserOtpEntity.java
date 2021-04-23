package com.alphacoder.multiauthentication.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "USER_OTP")
@Data
public class UserOtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID")
    private Integer id;

    @Column(name= "USERNAME")
    private String username;

    @Column(name= "OTP")
    private String otp;
}
