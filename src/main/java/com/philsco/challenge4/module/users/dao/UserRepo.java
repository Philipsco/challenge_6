package com.philsco.challenge4.module.users.dao;

import com.philsco.challenge4.module.users.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<UsersModel, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "insert into users(username, email, password) values(:username, :email, :password)")
    void addUser(@Param("username") String username,
                 @Param("email") String email,
                 @Param("password") String password);

    @Modifying
    @Query(nativeQuery = true, value = "update users set username= :username, email= :email, password= :password where user_id= :user_id")
    void updateUser(@Param("user_id") Integer userId, @Param("username") String username, @Param("email") String email, @Param("password") String password);

    @Modifying
    @Query(nativeQuery = true, value = "delete from users where user_id= :user_id")
    void deleteUser(@Param("user_id") Integer userId);

    @Query(nativeQuery = true, value = "select * from users where user_id= :user_id")
    UsersModel getUserById(@Param("user_id") Integer userId);

    @Query(value = "select * from users where username = :username", nativeQuery = true)
    UsersModel findByUsername(
            @Param("username") String username
    );

}
