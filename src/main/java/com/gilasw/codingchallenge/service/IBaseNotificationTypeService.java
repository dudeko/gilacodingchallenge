package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.NotificationType;

public interface IBaseNotificationTypeService {

    NotificationType getNotificationType();

    boolean isNotificationType(NotificationType notificationType);

    void send(NotificationDTO notificationDTO);
}
