package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.User;
import org.example.domain.Message;
import org.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String getMessage(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTextAndTitle(filter);
        } else {
            messages = messageRepo.findAllByOrderByCreatedAtDesc();
        }
        model.put("messages", messages);
        model.put("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String sendMessage(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("message", message);
            Iterable<Message> messages = messageRepo.findAllByOrderByCreatedAtDesc();
            model.addAttribute("messages", messages);
            return "main";
        } else {
            if (!file.isEmpty() && !file.getOriginalFilename().isEmpty()) {
                String resFileName = uploadedDir(file, uploadPath);
                message.setFilename(resFileName);
            }

            model.addAttribute("message", null);
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAllByOrderByCreatedAtDesc();
        model.addAttribute("messages", messages);

        return "redirect:main";
    }

    public static String uploadedDir(@RequestParam("file") MultipartFile file, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFileUUID = UUID.randomUUID().toString();
        String resFileName = uuidFileUUID + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resFileName));
        return resFileName;
    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        Set<Message> messages;
        if (!StringUtils.isEmpty(message)) {
            messages = user.getMessage(message.getId());
        } else {
            messages = user.getMessages();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("title") String title,
            @RequestParam("text") String text,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(title)) {
                message.setTitle(title);
            }
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if (!file.isEmpty() && !file.getOriginalFilename().isEmpty()) {
                String resFileName = uploadedDir(file, uploadPath);
                message.setFilename(resFileName);
            }
            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }
}