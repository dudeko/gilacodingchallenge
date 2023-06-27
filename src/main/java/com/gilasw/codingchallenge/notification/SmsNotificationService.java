package com.gilasw.codingchallenge.notification;

import org.springframework.stereotype.Service;

import static com.gilasw.codingchallenge.notification.NotificationType.SMS;

@Service
public class SmsNotificationService extends BaseNotificationService {

    @Override
    public NotificationType getNotificationType() {
        return SMS;
    }

    @Override
    public void send(NotificationDTO notificationDTO) {
        printOnFile(getNotificationType().name(), notificationDTO);
    }
}
