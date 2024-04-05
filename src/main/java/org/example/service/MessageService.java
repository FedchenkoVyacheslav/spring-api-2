package org.example.service;

import org.example.domain.Message;
import org.example.domain.User;
import org.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Page<Message> messageList(Pageable pageable, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTextContainingIgnoreCase(filter, pageable);
        } else {
            return messageRepo.findAll(pageable);
        }
    }

    public Page<Message> messageListForUser(Pageable pageable, User author) {
        return messageRepo.findByUser(pageable, author);
    }

    public Page<Message> messageById(Pageable pageable, User author, Long messageId) {
        List<Message> messages = messageRepo.findByUser(pageable, author).stream().filter(e -> e.getId().equals(messageId)).toList();
        return new PageImpl<>(messages);
    }
}
