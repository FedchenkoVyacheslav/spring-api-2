package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.domain.Message;
import org.example.domain.User;
import org.example.domain.dto.MessageDto;
import org.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTextContainingIgnoreCase(filter, pageable, user);
        } else {
            return messageRepo.findAll(pageable, user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, currentUser, author);
    }

    public Page<MessageDto> messageById(Pageable pageable, User currentUser, User author, Long messageId) {
        List<MessageDto> messages = messageRepo.findByUser(pageable, currentUser, author).stream().filter(e -> e.getId().equals(messageId)).toList();
        return new PageImpl<>(messages);
    }
}
