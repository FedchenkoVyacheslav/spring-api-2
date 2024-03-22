package org.example.service;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo UserRepo;

//    @Autowired
//    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = UserRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userDB = UserRepo.findByEmail(user.getEmail());
        if (userDB != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
//        user.setActivationCode(UUID.randomUUID().toString());

        UserRepo.save(user);
//        String message = String.format(
//                "Hello, %s %s! \n" +
//                        "Welcome to Sweater. Please, visit next link: http://localhost:8082/activate/%s",
//                user.getName(),
//                user.getSurname(),
//                user.getActivationCode()
//        );
//        mailSender.send(user.getEmail(), "Activation code", message);

        return true;
    }
}
