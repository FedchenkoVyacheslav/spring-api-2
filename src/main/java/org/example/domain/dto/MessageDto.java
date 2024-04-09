package org.example.domain.dto;

import lombok.Data;
import org.example.domain.Message;
import org.example.domain.User;
import org.example.domain.util.MessageHelper;

import java.util.Date;
@Data
public class MessageDto {
    private Long id;
    private String text;
    private String title;
    private User author;
    private String filename;
    private Date createdAt;
    private Date updatedAt;
    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.title = message.getTitle();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
        this.likes = likes;
        this.meLiked = meLiked;
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

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
