package org.example.repository;

import org.example.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTitleContainingIgnoreCase(String title);
    List<Message> findByTextContainingIgnoreCase (String text);
}