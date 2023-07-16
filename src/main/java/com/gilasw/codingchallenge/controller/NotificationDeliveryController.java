package com.gilasw.codingchallenge.controller;

import com.gilasw.codingchallenge.dto.ApiResponseDTO;
import com.gilasw.codingchallenge.exception.ParameterValidationException;
import com.gilasw.codingchallenge.json.NotificationJson;
import com.gilasw.codingchallenge.service.IMessageCategoryService;
import com.gilasw.codingchallenge.service.INotificationDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationDeliveryController {

    @Autowired
    private INotificationDeliveryService notificationDeliveryService;
    @Autowired
    private IMessageCategoryService messageCategoryService;

    @PostMapping("/send")
    public ApiResponseDTO send(@RequestBody NotificationJson notificationJson) {
        notificationJson.validateRequiredFields();
        validateCategoryExists(notificationJson.getCategory());
        notificationDeliveryService.send(notificationJson.getCategory(), notificationJson.getMessage());
        return ApiResponseDTO.success("Message was sent successfully.");
    }

    private void validateCategoryExists(String category) {
        if (messageCategoryService.doesNotExist(category)) {
            throw new ParameterValidationException("The " + category + " category does not exist.");
        }
    }
}
