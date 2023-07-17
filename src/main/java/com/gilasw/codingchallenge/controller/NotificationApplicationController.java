package com.gilasw.codingchallenge.controller;

import com.gilasw.codingchallenge.model.MessageCategory;
import com.gilasw.codingchallenge.model.NotificationLog;
import com.gilasw.codingchallenge.service.IMessageCategoryService;
import com.gilasw.codingchallenge.service.INotificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification")
public class NotificationApplicationController {

    @Autowired
    private INotificationLogService notificationLogService;
    @Autowired
    private IMessageCategoryService messageCategoryService;

    @GetMapping("/get-notification-log-history")
    public List<NotificationLog> getNitificationLogHistory(@RequestParam String language) {
        return notificationLogService
                .findAll().stream()
                .map(notificationLog -> notificationLog.withLocale(language))
                .sorted(Comparator.comparing(NotificationLog::getRegistrationDateTime).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/get-categories")
    public List<MessageCategory> getCategories() {
        return messageCategoryService.findAll();
    }
}
