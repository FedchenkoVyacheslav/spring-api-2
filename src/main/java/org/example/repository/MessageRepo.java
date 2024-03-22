package org.example.repository;

import org.example.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTitleContainingIgnoreCase(String title);

    List<Message> findByTextContainingIgnoreCase(String text);

    default Set<Message> findByTextAndTitle(String filter) {
        Set<Message> result = new HashSet<>();
        result.addAll(findByTitleContainingIgnoreCase(filter));
        result.addAll(findByTextContainingIgnoreCase(filter));
        return result;
    }
}