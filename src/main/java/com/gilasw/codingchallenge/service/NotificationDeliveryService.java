package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.NotificationType;
import com.gilasw.codingchallenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gilasw.codingchallenge.dto.NotificationDTO.create;

@Service
public class NotificationDeliveryService implements INotificationDeliveryService {

    @Autowired
    private List<IBaseNotificationTypeService> notificationServiceList;
    @Autowired
    private IUserService userService;

    public void send(String category, String message) {
        List<User> userList = userService.findWithCategory(category);
        userList.forEach(user -> {
            user.getChannels().forEach(notificationType -> getNotificationService(notificationType).send(create(user, category, message)));
        });
    }

    private IBaseNotificationTypeService getNotificationService(NotificationType notificationType) {
        return notificationServiceList.stream()
                .filter(baseNotificationService -> baseNotificationService.isNotificationType(notificationType))
                .findFirst()
                .orElse(null);
    }
}
