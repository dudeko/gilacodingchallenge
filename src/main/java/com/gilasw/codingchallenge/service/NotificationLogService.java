package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.NotificationLog;
import com.gilasw.codingchallenge.repository.NotificationLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationLogService implements INotificationLogService {

    private final Logger logger = LoggerFactory.getLogger(NotificationLogService.class);

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Override
    public void save(NotificationLog notificationLog) {
        notificationLogRepository.save(notificationLog);
        logger.info(notificationLog.getLogText());
    }

    @Override
    public List<NotificationLog> findAll() {
        return notificationLogRepository.findAll();
    }
}
