package org.example.controller;

import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("profile")
    public String userEditForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("profile")
    public String saveUser(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String location,
            @RequestParam(required = false, defaultValue = "") Integer age,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        userService.updateProfile(user, name, surname, location, age, file);

        return "redirect:/user/profile";
    }

    @GetMapping("subscribe/{author}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model
    ) {
        userService.subscribe(currentUser, author);
        model.addAttribute("isCurrentUser", currentUser.equals(author));

        return "redirect:/user-messages/" + author.getId();
    }

    @GetMapping("unsubscribe/{author}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model
    ) {
        userService.unsubscribe(currentUser, author);
        model.addAttribute("isCurrentUser", currentUser.equals(author));

        return "redirect:/user-messages/" + author.getId();
    }

    @GetMapping("{type}/{author}/list")
    public String userList(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            @PathVariable String type,
            Model model
    ) {
        model.addAttribute("userChannel", author);
        model.addAttribute("type", type);
        model.addAttribute("isCurrentUser", currentUser.equals(author));

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", author.getSubscriptions());
        } else {
            model.addAttribute("users", author.getFollowers());
        }

        return "subscriptions";
    }
}
