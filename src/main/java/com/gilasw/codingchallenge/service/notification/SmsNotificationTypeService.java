package com.gilasw.codingchallenge.service.notification;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.NotificationType;
import com.gilasw.codingchallenge.service.BaseNotificationTypeService;
import org.springframework.stereotype.Service;

import static com.gilasw.codingchallenge.model.NotificationType.SMS;

@Service
public class SmsNotificationTypeService extends BaseNotificationTypeService {

    @Override
    public NotificationType getNotificationType() {
        return SMS;
    }

    @Override
    public void send(NotificationDTO notificationDTO) {
        printOnFile(notificationDTO.notificationType(this.getNotificationType().name()));
    }
}
