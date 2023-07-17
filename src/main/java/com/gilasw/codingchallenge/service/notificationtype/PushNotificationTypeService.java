package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.model.NotificationLog;
import com.gilasw.codingchallenge.model.NotificationType;
import org.springframework.stereotype.Service;

import static com.gilasw.codingchallenge.model.NotificationType.PUSH;


@Service
public class PushNotificationTypeService extends BaseNotificationTypeService {

    @Override
    public NotificationType getNotificationType() {
        return PUSH;
    }
}
