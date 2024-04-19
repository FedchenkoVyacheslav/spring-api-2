package org.example.controller;

import org.example.domain.User;
import org.example.domain.Role;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String findUser(@RequestParam(required = false, defaultValue = "") String filter,
                           Model model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<User> page = userService.findUsers(filter, pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin");
        model.addAttribute("filter", filter);

        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin";
    }

    @PostMapping
    public String saveUser(
            @RequestParam String email,
            @RequestParam Map<String, String> form,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, email, form);

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams().forEach(redirectAttributes::addAttribute);

        return "redirect:" + components.getPath();
    }
}
