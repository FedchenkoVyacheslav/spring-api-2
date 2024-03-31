package org.example.repository;

import org.example.domain.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTitleContainingIgnoreCase(String title);

    List<Message> findByTextContainingIgnoreCase(String text);

    List<Message> findAllByOrderByCreatedAtDesc();

    default Set<Message> findByTextAndTitle(String filter) {
        Set<Message> result = new HashSet<>();
        result.addAll(findByTitleContainingIgnoreCase(filter));
        result.addAll(findByTextContainingIgnoreCase(filter));
        return result;
    }
}