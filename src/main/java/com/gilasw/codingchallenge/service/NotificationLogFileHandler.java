package com.gilasw.codingchallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilasw.codingchallenge.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.lineSeparator;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Component
public class NotificationLogFileHandler {

    private final Logger logger = LoggerFactory.getLogger(NotificationLogFileHandler.class);

    public void printOnFile(NotificationDTO notificationDTO) {
        try {
            Files.writeString(Paths.get("notification_log.txt"), notificationDTO.toString() + lineSeparator(), CREATE, APPEND);
            logger.info(notificationDTO.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
