package com.badmintonmanagement.repository;

import com.badmintonmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);

    @Query("select u from User u where u.fullName = :fullName")
    User findByName(String fullName);
}
