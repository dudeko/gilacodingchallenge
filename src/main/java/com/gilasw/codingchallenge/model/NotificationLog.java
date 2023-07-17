package com.gilasw.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static java.time.chrono.Chronology.ofLocale;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;
import static java.time.format.FormatStyle.SHORT;
import static java.util.Locale.getDefault;

@Document("notificationLog")
public class NotificationLog {

    @Id
    private String id;
    private LocalDateTime registrationDateTime;
    private User user;
    private String category;
    private String message;
    private NotificationType notificationType;
    private String language;

    public static NotificationLog create(User user, String category, String message) {
        NotificationLog notificationLog = new NotificationLog();
        notificationLog.setRegistrationDateTime(LocalDateTime.now());
        notificationLog.setUser(user);
        notificationLog.setCategory(category);
        notificationLog.setMessage(message);
        return notificationLog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
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

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationLog withNotificationType(NotificationType notificationType) {
        this.setNotificationType(notificationType);
        return this;
    }

    @Transient
    private String getFormatterRegistrationDateTime() {
        return this.getRegistrationDateTime().format(ofLocalizedDateTime(SHORT).withLocale(getLocale()).withChronology(ofLocale(getLocale())));
    }

    @JsonInclude
    @Transient
    public String getLogText() {
        return this.toString();
    }

    @Transient
    public Locale getLocale() {
        return language != null ? Locale.forLanguageTag(language) : Locale.getDefault();
    }

    @Override
    public String toString() {
        return "[" + this.getFormatterRegistrationDateTime() + "]"
                + " - "
                + this.getNotificationType().name()
                + " (" + this.getCategory() + ")"
                + " to "
                + this.getUser().getName()
                + ": "
                + this.getMessage();
    }

    public NotificationLog withLocale(String language) {
        this.language = language;
        return this;
    }
}
