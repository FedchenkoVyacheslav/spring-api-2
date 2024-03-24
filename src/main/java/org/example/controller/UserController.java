package org.example.controller;

import org.example.domain.User;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo UserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping
    public String saveUser(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String location,
            @RequestParam(required = false, defaultValue = "") Integer age,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") User user
    ) throws IOException {
        user.setName(name);
        user.setSurname(surname);
        user.setLocation(location);
        user.setAge(age);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (!file.isEmpty() && !file.getOriginalFilename().isEmpty()) {
            String resFileName = MainController.uploadedDir(file, uploadPath);
            user.setPhotoUrl(resFileName);
        }
        UserRepo.save(user);

        return "redirect:/main";
    }
}
