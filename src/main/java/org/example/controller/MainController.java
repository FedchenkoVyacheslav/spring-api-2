package org.example.controller;

import org.example.domain.Identity;
import org.example.domain.Message;
import org.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String getMessage(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String sendMessage(@AuthenticationPrincipal Identity author,
                              @RequestParam String text,
                              @RequestParam String tag,
                              Map<String, Object> model) {
        Message message = new Message(text, tag, author);
        if (text.trim().length() == 0 || tag.trim().length() == 0) {
            Iterable<Message> messages = messageRepo.findAll();
            model.put("error", "Empty message or tag!");
            model.put("messages", messages);
            return "main";
        } else {
            model.remove("error");
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "redirect:main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !(filter.trim().length() == 0) && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
            model.remove("searchError");
        } else {
            model.put("searchError", "Empty search query!");
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}