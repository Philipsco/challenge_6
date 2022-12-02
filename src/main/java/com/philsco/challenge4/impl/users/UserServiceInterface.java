package com.philsco.challenge4.impl.users;

import com.philsco.challenge4.module.users.model.UsersModel;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {
    void addUser(UsersModel usersModel);

    void deleteUser(Integer userId);

    void updateUser(Integer userId, UsersModel usersModel);

    UsersModel getUserById(Integer userId);
}