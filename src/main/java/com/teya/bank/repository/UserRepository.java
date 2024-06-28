package com.teya.bank.repository;


import com.teya.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.accounts WHERE u.id = :id" )
    Optional<User> findUserWithDetailedAccounts(@Param("id") Long id);

}
