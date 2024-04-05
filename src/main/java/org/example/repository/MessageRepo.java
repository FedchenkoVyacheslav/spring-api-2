package org.example.repository;

import org.example.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Page<Message> findByTextContainingIgnoreCase(String text, Pageable pageable);

    List<Message> findAllByOrderByCreatedAtDesc();

    Page<Message> findAll(Pageable pageable);
}