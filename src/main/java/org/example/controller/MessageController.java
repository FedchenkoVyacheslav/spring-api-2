package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.User;
import org.example.domain.Message;
import org.example.repository.MessageRepo;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import java.util.UUID;

@Controller
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageService messageService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String getMessage(@RequestParam(required = false, defaultValue = "") String filter,
                             Model model,
                             @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Message> page = messageService.messageList(pageable, filter);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String sendMessage(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("message", message);
            Page<Message> messages = messageRepo.findAll(pageable);
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

        Page<Message> messages = messageRepo.findAll(pageable);
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

    @GetMapping("/user-messages/{author}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Message> page;

        if (!StringUtils.isEmpty(message)) {
            page = messageService.messageById(pageable, author, message.getId());
        } else {
            page = messageService.messageListForUser(pageable, author);
        }
        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("followersCount", author.getFollowers().size());
        model.addAttribute("isSubscriber", author.getFollowers().contains(currentUser));
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-messages/" + author.getId());

        return "userMessages";
    }

    @PostMapping("/user-messages/{author}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long author,
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

        return "redirect:/user-messages/" + author;
    }

    @GetMapping("/del-user-messages/{author}")
    public String deleteMessage(
            @PathVariable Long author,
            @RequestParam Message message
    ) {
        messageRepo.delete(message);
        return "redirect:/user-messages/" + author;
    }
}