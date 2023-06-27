package com.gilasw.codingchallenge.notification;

public interface IBaseNotificationService {

    NotificationType getNotificationType();

    boolean isNotificationType(NotificationType notificationType);

    void send(NotificationDTO notificationDTO);
}
