package com.example.SecurityLearning.repository;

import com.example.SecurityLearning.entity.User;
import com.example.SecurityLearning.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByProviderIdAndProviderType(
            String providerId,
            AuthProviderType providerType
    );

}