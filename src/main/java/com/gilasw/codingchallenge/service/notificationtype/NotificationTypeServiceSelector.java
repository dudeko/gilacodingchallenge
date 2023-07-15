package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.model.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationTypeServiceSelector {

    private final Logger logger = LoggerFactory.getLogger(NotificationTypeServiceSelector.class);

    @Autowired
    private Map<String, IBaseNotificationTypeService> notificationTypeServiceMap;

    @Autowired
    private void setNotificationTypeServiceMap(Map<String, IBaseNotificationTypeService> notificationTypeServiceMap) {
        this.notificationTypeServiceMap = notificationTypeServiceMap
                .values().stream()
                .collect(Collectors.toMap(this::getNotificationTypeString, notificationTypeService -> notificationTypeService));
    }

    private String getNotificationTypeString(IBaseNotificationTypeService notificationTypeService) {
        return notificationTypeService.getNotificationType().toString();
    }

    public IBaseNotificationTypeService getNotificationService(NotificationType notificationType) {
        IBaseNotificationTypeService notificationTypeService =  notificationTypeServiceMap.get(notificationType.toString());
        if (notificationTypeService == null) {
            logger.warn("The " + notificationType + " notificationType does not have a service implementation.");
        }
        return notificationTypeService;
    }
}
