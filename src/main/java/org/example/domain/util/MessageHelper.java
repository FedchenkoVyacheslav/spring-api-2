package org.example.domain.util;

import org.example.domain.User;

public abstract class MessageHelper {
    public static String getAuthor(User author) {
        return author.getUsername();
    }

    public static String getAuthorName(User author) {
        return author.getName();
    }

    public static String getAuthorSurname(User author) {
        return author.getSurname();
    }

    public static String getAuthorAvatar(User author) {
        return author.getPhotoUrl();
    }
}
