package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.domain.util.MessageHelper;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long")
    private String text;
    @NotBlank(message = "Please fill the message")
    @Length(max = 255, message = "Title too long")
    private String title;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String filename;
    @ManyToMany
    @JoinTable(
            name = "message_likes",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likes = new HashSet<>();

    public Message() {
    }

    public Message(String text, String title, User author) {
        this.author = author;
        this.text = text;
        this.title = title;
    }

    public String getAuthorEmail() {
        return MessageHelper.getAuthor(author);
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    public String getAuthorSurname() {
        return MessageHelper.getAuthorSurname(author);
    }

    public String getAuthorAvatar() {
        return MessageHelper.getAuthorAvatar(author);
    }
}
