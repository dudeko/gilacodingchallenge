package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.lineSeparator;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public abstract class BaseNotificationTypeService implements IBaseNotificationTypeService {

    Logger logger = LoggerFactory.getLogger(BaseNotificationTypeService.class);

    @Override
    public abstract NotificationType getNotificationType();

    @Override
    public boolean isNotificationType(NotificationType notificationType) {
        return this.getNotificationType().equals(notificationType);
    }

    @Override
    public abstract void send(NotificationDTO notificationDTO);

    protected void printOnFile(NotificationDTO notificationDTO) {
        try {
            Files.writeString(Paths.get("notification_log.txt"), notificationDTO.toString() + lineSeparator(), CREATE, APPEND);
            logger.info(notificationDTO.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
