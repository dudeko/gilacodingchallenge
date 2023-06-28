package com.gilasw.codingchallenge.controller;

import com.gilasw.codingchallenge.dto.ApiResponseDTO;
import com.gilasw.codingchallenge.exception.ParameterValidationException;
import com.gilasw.codingchallenge.service.IMessageCategoryService;
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
    @Autowired
    private IMessageCategoryService messageCategoryService;

    @PostMapping("/send")
    public ApiResponseDTO send(@RequestParam String category, @RequestParam String message) {
        validateCategory(category);
        validateMessage(message);
        notificationDeliiveryService.send(category, message);
        return ApiResponseDTO.success("Message was sent successfully.");
    }

    private void validateCategory(String category) {
        validateRequiredField("category", category);
        validateCategoryExists(category);
    }

    private void validateCategoryExists(String category) {
        if (!messageCategoryService.doesCategoryExist(category)) {
            throw new ParameterValidationException("The " + category + " category does not exist.");
        }
    }

    private void validateMessage(String message) {
        validateRequiredField("message", message);
    }

    private void validateRequiredField(String fieldName, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ParameterValidationException("The "+ fieldName + " should not be empty.");
        }
    }
}
