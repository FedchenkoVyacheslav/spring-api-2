package org.example.controller;

import org.example.domain.User;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo UserRepo;

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping
    public String saveUser(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String location,
            @RequestParam int age,
            @RequestParam("userId") User user
    ){
        user.setName(name);
        user.setSurname(surname);
        user.setLocation(location);
        user.setAge(age);
        UserRepo.save(user);

        return "redirect:/main";
    }
}
