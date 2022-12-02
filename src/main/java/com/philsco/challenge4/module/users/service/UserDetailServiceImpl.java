package com.philsco.challenge4.module.users.service;

import com.philsco.challenge4.module.users.dao.UserRepo;
import com.philsco.challenge4.module.users.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = usersRepository.findByUsername(username);
        return UserDetailsImpl.build(user);
    }

}
