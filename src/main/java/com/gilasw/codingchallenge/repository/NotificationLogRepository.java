package com.gilasw.codingchallenge.repository;

import com.gilasw.codingchallenge.model.MessageCategory;
import com.gilasw.codingchallenge.model.NotificationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {

}
