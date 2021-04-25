package com.alphacoder.multiauthentication.repository;

import com.alphacoder.multiauthentication.entity.UserOtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<UserOtpEntity, String> {

}
