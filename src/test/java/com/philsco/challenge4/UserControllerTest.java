package com.philsco.challenge4;

import com.philsco.challenge4.module.users.controller.UserController;
import com.philsco.challenge4.module.users.dto.AddUserDTO;
import com.philsco.challenge4.module.users.dto.UpdateUserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    @DisplayName("1. Menambahkan user baru")
    void addUser() {
        AddUserDTO newUser = new AddUserDTO("orangBarunich1", "orangBarunich1@email.com", "orangBarunich1pass");
        userController.addUser(newUser);
    }

    @Test
    @DisplayName("2. Mengupdate data User")
    void updateUser() {
        UpdateUserDTO updatedUser = new UpdateUserDTO(1, "apdetnih@email.com", "dawfawed", "sdawefasdcawd");
        userController.updateUser(updatedUser.getUserId(), updatedUser);
    }

    @Test
    @DisplayName("3. Menghapus user")
    void deleteUser() {
        userController.deleteUser(1);
    }

}
