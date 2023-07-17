package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.NotificationLog;

import java.util.List;

public interface INotificationLogService {

    void save(NotificationLog notificationLog);

    List<NotificationLog> findAll();
}
