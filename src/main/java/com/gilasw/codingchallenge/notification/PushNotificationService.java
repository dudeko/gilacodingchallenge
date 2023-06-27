package com.gilasw.codingchallenge.notification;

import org.springframework.stereotype.Service;

import static com.gilasw.codingchallenge.notification.NotificationType.PUSH;


@Service
public class PushNotificationService extends BaseNotificationService {

    @Override
    public NotificationType getNotificationType() {
        return PUSH;
    }

    @Override
    public void send(NotificationDTO notificationDTO) {
        printOnFile(getNotificationType().name(), notificationDTO);
    }
}
