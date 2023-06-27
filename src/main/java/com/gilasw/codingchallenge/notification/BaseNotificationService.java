package com.gilasw.codingchallenge.notification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static java.lang.System.lineSeparator;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public abstract class BaseNotificationService implements IBaseNotificationService {

    @Override
    public abstract NotificationType getNotificationType();

    @Override
    public boolean isNotificationType(NotificationType notificationType) {
        return this.getNotificationType().equals(notificationType);
    }

    @Override
    public abstract void send(NotificationDTO notificationDTO);

    protected void printOnFile(String notificationType, NotificationDTO notificationDTO) {
        try {
            Files.writeString(Paths.get("notification_log.txt"), formatMessage(notificationType, notificationDTO), CREATE, APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatMessage(String notificationType, NotificationDTO notificationDTO) {
        return new Date()
                + " - "
                + notificationType
                + " (" + notificationDTO.getCategory() + ")"
                + " to "
                + notificationDTO.getUser().getName()
                + ": "
                + notificationDTO.getMessage()
                + lineSeparator();
    }
}
