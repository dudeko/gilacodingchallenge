package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.NotificationType;

public interface IBaseNotificationTypeService {

    NotificationType getNotificationType();

    void send(NotificationDTO notificationDTO) throws Exception;

    void sendAndLogExceptions(NotificationDTO notificationDTO);
}
