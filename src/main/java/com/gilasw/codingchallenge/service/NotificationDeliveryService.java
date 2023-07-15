package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.service.notificationtype.NotificationTypeServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.gilasw.codingchallenge.dto.NotificationDTO.create;

@Service
public class NotificationDeliveryService implements INotificationDeliveryService {

    @Autowired
    private NotificationTypeServiceSelector notificationServiceSelector;
    @Autowired
    private UserService userService;

    public void send(String category, String message) {
        userService.findWithCategory(category).forEach(user -> {
            user.getChannels().stream()
                .map(notificationServiceSelector::getNotificationService)
                .filter(Objects::nonNull)
                .forEach(notificationService -> notificationService.sendAndLogExceptions(create(user, category, message)));
        });
    }
}
