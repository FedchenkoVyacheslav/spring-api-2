package org.example.repository;

import org.example.domain.Message;
import org.springframework.data.repository.CrudRepository;
public interface MessageRepo extends CrudRepository<Message, Integer> {
}