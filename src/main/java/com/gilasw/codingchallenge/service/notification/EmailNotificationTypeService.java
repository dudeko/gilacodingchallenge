package com.gilasw.codingchallenge.service.notification;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.NotificationType;
import com.gilasw.codingchallenge.service.BaseNotificationTypeService;
import org.springframework.stereotype.Service;

import static com.gilasw.codingchallenge.model.NotificationType.EMAIL;

@Service
public class EmailNotificationTypeService extends BaseNotificationTypeService {

    @Override
    public NotificationType getNotificationType() {
        return EMAIL;
    }

    @Override
    public void send(NotificationDTO notificationDTO) {
        printOnFile(notificationDTO.notificationType(this.getNotificationType().name()));
    }
}
