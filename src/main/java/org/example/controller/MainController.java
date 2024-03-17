package org.example.controller;

import org.example.domain.Identity;
import org.example.domain.Message;
import org.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String getMessage(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        model.put("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String sendMessage(@AuthenticationPrincipal Identity author,
                              @RequestParam String text,
                              @RequestParam String tag,
                              @RequestParam Map<String, Object> model,
                              @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = new Message(text.trim(), tag.trim(), author);

        if (!file.isEmpty() && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFileUUID = UUID.randomUUID().toString();
            String resFileName = uuidFileUUID + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resFileName));

            message.setFilename(resFileName);
        }

        messageRepo.save(message);
        model.put("messages", messageRepo.findAll());

        return "redirect:main";
    }
}