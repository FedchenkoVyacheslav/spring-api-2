package org.example.controller;

import org.example.domain.User;
import org.example.domain.Role;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo UserRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userDB = UserRepo.findByEmail(user.getEmail());
        if (user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            model.put("message", "Empty email or password!");
            return "registration";
        }
        if (userDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        UserRepo.save(user);

        return "redirect:/login";
    }
}
