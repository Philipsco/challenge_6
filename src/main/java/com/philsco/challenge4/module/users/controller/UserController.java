package com.philsco.challenge4.module.users.controller;

import com.philsco.challenge4.module.films.dto.AddFilmDTO;
import com.philsco.challenge4.module.films.dto.UpdateFilmDTO;
import com.philsco.challenge4.module.users.dto.AddUserDTO;
import com.philsco.challenge4.module.users.dto.UpdateUserDTO;
import com.philsco.challenge4.module.users.mapping.UserMapping;
import com.philsco.challenge4.module.users.model.UsersModel;
import com.philsco.challenge4.module.users.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/cinema/users")
public class UserController {
    private HashMap<String, Object> data = new HashMap<String, Object>();

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserMapping userMapping;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Operation(summary = "Add a user, add 'username', 'email', and 'password' and program will assign the new userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Added!",
                    content = {@Content(schema = @Schema(example = "User Added!"))})
    })
    @PostMapping("")
    public ResponseEntity<HashMap<String, Object>> addUser(@RequestBody AddUserDTO newUser) {
        userServiceImpl.addUser(userMapping.addUser(newUser));
        LOG.info("User added with email: {}", newUser.getEmail());
        data.put("data", newUser);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Change a user data, update 'username', 'email', and 'password' related to their userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated!",
                    content = {@Content(schema = @Schema(example = "User updated!"))})
    })
    @PutMapping("/customer/{user_id}/update")
    public ResponseEntity<HashMap<String, Object>> updateUser(@PathVariable("user_id") Integer userId, @RequestBody UpdateUserDTO updateUser){
        userServiceImpl.updateUser(userId, userMapping.updateUSer(updateUser));
        LOG.info("user updated");
        data.put("data",updateUser);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Delete a user by their userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User deleted!",
                    content = {@Content(schema = @Schema(example = "User deleted!"))})
    })
    @DeleteMapping("/customer/{user_id}")
    public ResponseEntity<HttpStatus> deleteUser (@PathVariable("user_id") Integer userId){
        userServiceImpl.deleteUser(userId);
        LOG.info("user deleted");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/public/get-user/{id}")
    public ResponseEntity<UsersModel> getUserById(@PathVariable("id") Integer userId) {
        UsersModel user = userServiceImpl.getUserById(userId);
        LOG.info("User found: {}", user.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
