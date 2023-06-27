package com.gilasw.codingchallenge.notification;

import com.gilasw.codingchallenge.model.User;

public class NotificationDTO {

    private User user;
    private String category;
    private String message;

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
}
