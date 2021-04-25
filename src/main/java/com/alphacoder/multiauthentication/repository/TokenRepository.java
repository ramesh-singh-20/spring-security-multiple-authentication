package com.alphacoder.multiauthentication.repository;

import com.alphacoder.multiauthentication.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<UserTokenEntity, String> {
}
