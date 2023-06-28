package com.gilasw.codingchallenge.dto;

import com.gilasw.codingchallenge.model.User;

import java.util.Date;

import static java.lang.System.lineSeparator;

public class NotificationDTO {

    private User user;
    private String category;
    private String message;
    private String notificationType;

    public static NotificationDTO create(User user, String category, String message) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setUser(user);
        notificationDTO.setCategory(category);
        notificationDTO.setMessage(message);
        return notificationDTO;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationDTO notificationType(String notificationType) {
        this.setNotificationType(notificationType);
        return this;
    }

    @Override
    public String toString() {
        return new Date()
                + " - "
                + this.getNotificationType()
                + " (" + this.getCategory() + ")"
                + " to "
                + this.getUser().getName()
                + ": "
                + this.getMessage();
    }
}
