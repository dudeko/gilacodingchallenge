package com.gilasw.codingchallenge.model;

import com.gilasw.codingchallenge.notification.NotificationType;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<MessageCategory> subscribed;
    private List<NotificationType> channels;

    public static User create() {
        return new User();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User id(Long id) {
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User name(String name) {
        this.setName(name);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User email(String email) {
        this.setEmail(email);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public List<MessageCategory> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<MessageCategory> subscribed) {
        this.subscribed = subscribed;
    }

    public User subscribed(List<MessageCategory> subscribed) {
        this.setSubscribed(subscribed);
        return this;
    }

    public List<NotificationType> getChannels() {
        return channels;
    }

    public void setChannels(List<NotificationType> channels) {
        this.channels = channels;
    }

    public User channels(List<NotificationType> channels) {
        this.setChannels(channels);
        return this;
    }

    public boolean hasCategory(String category) {
        return subscribed.stream().anyMatch(messageCategory -> messageCategory.getName().equals(category));
    }
}
