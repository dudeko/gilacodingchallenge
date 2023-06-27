package com.gilasw.codingchallenge.notification;

import org.springframework.stereotype.Service;

import static com.gilasw.codingchallenge.notification.NotificationType.EMAIL;

@Service
public class EmailNotificationService extends BaseNotificationService {

    @Override
    public NotificationType getNotificationType() {
        return EMAIL;
    }

    @Override
    public void send(NotificationDTO notificationDTO) {
        printOnFile(getNotificationType().name(), notificationDTO);
    }
}
