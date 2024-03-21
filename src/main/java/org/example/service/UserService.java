package org.example.service;

import org.example.domain.User;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo UserRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = UserRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }
        return user;
    }
}
