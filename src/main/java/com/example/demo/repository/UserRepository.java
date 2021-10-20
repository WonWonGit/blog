package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
    //JPA Naming
    //Select * from user Where user_name=? and password=?;
//    User findByUsernameAndPassword(String userName, String password);

//    @Query(value = "Select * from user Where user_name=?1 and password=?2;", nativeQuery = true)
//    User login(String userName, String password);
