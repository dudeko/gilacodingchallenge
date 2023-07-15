package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.NotificationType;
import com.gilasw.codingchallenge.service.NotificationLogFileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseNotificationTypeService implements IBaseNotificationTypeService {

    private final Logger logger = LoggerFactory.getLogger(BaseNotificationTypeService.class);

    @Autowired
    protected NotificationLogFileHandler notificationLogFileHandler;

    @Override
    public abstract NotificationType getNotificationType();

    @Override
    public abstract void send(NotificationDTO notificationDTO);

    @Override
    public void sendAndLogExceptions(NotificationDTO notificationDTO) {
        try {
            this.send(notificationDTO);
        } catch (Exception ex) {
            logger.error("Unable to send notification: " + getExceptionNameAndMessage(ex));
        }
    }

    private static String getExceptionNameAndMessage(Exception ex) {
        return "[" + ex.getClass().getSimpleName() + "] " + ex.getMessage();
    }
}
