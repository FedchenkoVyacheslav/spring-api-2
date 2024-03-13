package org.example.controller;

import org.example.domain.Identity;
import org.example.domain.Role;
import org.example.repository.IdentityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private IdentityRepo identityRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Identity identity, Map<String, Object> model){
        Identity identityDB = identityRepo.findByUsername(identity.getUsername());
        if(identityDB != null) {
            model.put("message", "User exists");
            return "registration";
        }
        identity.setActive(true);
        identity.setRoles(Collections.singleton(Role.USER));
        identityRepo.save(identity);

        return "redirect:/login";
    }
}
