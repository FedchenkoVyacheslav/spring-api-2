package org.example.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String title;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String filename;

    public Message() {
    }

    public Message(String text, String title, User author) {
        this.author = author;
        this.text = text;
        this.title = title;
    }

    public String getAuthorEmail() {
        return author != null ? author.getEmail() : "No author";
    }

    public String getAuthorName() {
        return author != null ? author.getName() : "No author";
    }

    public String getAuthorSurname() {
        return author != null ? author.getSurname() : "No author";
    }
}
