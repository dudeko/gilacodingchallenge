package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.model.NotificationLog;
import com.gilasw.codingchallenge.model.NotificationType;

public interface IBaseNotificationTypeService {

    NotificationType getNotificationType();

    void send(NotificationLog notificationLog) throws Exception;

    void sendAndLogExceptions(NotificationLog notificationLog);
}
