package org.example.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String title;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
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
        return author.getEmail();
    }

    public String getAuthorName() {
        return author.getName();
    }

    public String getAuthorSurname() {
        return author.getSurname();
    }

    public String getAuthorAvatar() {
        return author.getPhotoUrl();
    }
}
