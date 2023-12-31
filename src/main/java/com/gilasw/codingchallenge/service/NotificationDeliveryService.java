package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.service.notificationtype.NotificationTypeServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.gilasw.codingchallenge.model.NotificationLog.create;

@Service
public class NotificationDeliveryService implements INotificationDeliveryService {

    @Autowired
    private NotificationTypeServiceSelector notificationTypeServiceSelector;
    @Autowired
    private UserService userService;

    public void send(String category, String message) {
        userService.findWithCategory(category).forEach(user -> {
            user.getChannels().stream()
                .map(notificationTypeServiceSelector::getNotificationService)
                .filter(Objects::nonNull)
                .forEach(notificationService -> notificationService.sendAndLogExceptions(create(user, category, message)));
        });
    }
}
