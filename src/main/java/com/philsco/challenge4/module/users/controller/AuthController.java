package com.philsco.challenge4.module.users.controller;

import com.philsco.challenge4.configuration.JWTUtils;
import com.philsco.challenge4.enumeration.ERole;
import com.philsco.challenge4.module.messages.JwtResponse;
import com.philsco.challenge4.module.messages.MessageResponse;
import com.philsco.challenge4.module.users.SignUpReq;
import com.philsco.challenge4.module.users.dao.RoleRepo;
import com.philsco.challenge4.module.users.dao.UserRepo;
import com.philsco.challenge4.module.users.model.RolesModels;
import com.philsco.challenge4.module.users.model.UsersModel;
import com.philsco.challenge4.module.users.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/cinema/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo usersRepository;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid
                                                        @RequestBody Map<String, Object> login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.get("username"), login.get("password"))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid
                                                        @RequestBody SignUpReq signupRequest) {
        Boolean usernameExists = usersRepository.existsByUsername(signupRequest.getUsername());
        if (Boolean.TRUE.equals(usernameExists)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Boolean emailExists = usersRepository.existsByEmail(signupRequest.getEmail());
        if (Boolean.TRUE.equals(emailExists)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        UsersModel users = new UsersModel(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<RolesModels> roles = new HashSet<>();

        if (strRoles == null) {
            RolesModels role = roleRepository.findByName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                RolesModels mRole = roleRepository.findByName(ERole.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found"));
                roles.add(mRole);
            });
        }
        users.setRoles(roles);
        usersRepository.save(users);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

}
