package com.squarehelp.squarehelp.repositories;


import com.squarehelp.squarehelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    //SQL query returns list of all users from search term.
//    @Query("SELECT username FROM User  WHERE username LIKE LOWER(CONCAT('%',:username,'%') ) ")
//    List<User> findAllUsersByUsername(@Param("username") String username);

    List<User> findByUsernameContaining(String username);
}
