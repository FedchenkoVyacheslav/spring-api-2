package org.example.controller;

import org.example.domain.Identity;
import org.example.domain.Role;
import org.example.repository.IdentityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IdentityRepo identityRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", identityRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable Identity user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "editUser";
    }
}
