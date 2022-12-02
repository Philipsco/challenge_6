package com.philsco.challenge4.module.users.service;

import com.philsco.challenge4.impl.users.UserServiceInterface;
import com.philsco.challenge4.module.users.dao.UserRepo;
import com.philsco.challenge4.module.users.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    public UserRepo userRepo;

    @Override
    public void addUser(UsersModel usersModel) {
        String username = usersModel.getUsername();
        String email = usersModel.getEmail();
        String password = usersModel.getPassword();
        userRepo.addUser(username,email,password);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepo.deleteUser(userId);
    }

    @Override
    public void updateUser(Integer userId, UsersModel usersModel) {
        String username = usersModel.getUsername();
        String email = usersModel.getEmail();
        String password = usersModel.getPassword();
        userRepo.updateUser(userId, username,email,password);
    }

    @Override
    public UsersModel getUserById(Integer userId) {
        return userRepo.getUserById(userId);
    }

}
