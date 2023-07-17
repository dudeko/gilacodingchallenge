package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.model.NotificationLog;
import com.gilasw.codingchallenge.model.NotificationType;
import com.gilasw.codingchallenge.service.NotificationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseNotificationTypeService implements IBaseNotificationTypeService {

    private final Logger logger = LoggerFactory.getLogger(BaseNotificationTypeService.class);

    @Autowired
    protected NotificationLogService notificationLogService;

    @Override
    public abstract NotificationType getNotificationType();

    @Override
    public void send(NotificationLog notificationLog) {
        notificationLogService.save(notificationLog.withNotificationType(this.getNotificationType()));
    }

    @Override
    public void sendAndLogExceptions(NotificationLog notificationLog) {
        try {
            this.send(notificationLog);
        } catch (Exception ex) {
            logger.error("Unable to send notification: " + getExceptionNameAndMessage(ex));
        }
    }

    private static String getExceptionNameAndMessage(Exception ex) {
        return "[" + ex.getClass().getSimpleName() + "] " + ex.getMessage();
    }
}
