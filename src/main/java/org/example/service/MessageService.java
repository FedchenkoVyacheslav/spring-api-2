package org.example.service;

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

    public Page<MessageDto> messageListForUser(Pageable pageable, User author, User currentUser) {
        return messageRepo.findByUser(pageable, author, currentUser);
    }

    public Page<MessageDto> messageById(Pageable pageable, User author, User currentUser, Long messageId) {
        List<MessageDto> messages = messageRepo.findByUser(pageable, author, currentUser).stream().filter(e -> e.getId().equals(messageId)).toList();
        return new PageImpl<>(messages);
    }
}
