package com.gilasw.codingchallenge.controller;

import com.gilasw.codingchallenge.service.INotificationDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationDeliveryController {

    @Autowired
    private INotificationDeliveryService notificationDeliiveryService;

    @PostMapping("/send")
    public void send(@RequestParam String category, @RequestParam String message) {
        notificationDeliiveryService.send(category, message);
    }
}
