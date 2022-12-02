package com.philsco.challenge4.module.users.mapping;

import com.philsco.challenge4.module.users.dto.AddUserDTO;
import com.philsco.challenge4.module.users.dto.UpdateUserDTO;
import com.philsco.challenge4.module.users.model.UsersModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {
    public UsersModel addUser(AddUserDTO req){
        UsersModel user = new UsersModel();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        return user;
    }

    public UsersModel updateUSer(UpdateUserDTO req){
        UsersModel user = new UsersModel();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        return user;
    }
}
