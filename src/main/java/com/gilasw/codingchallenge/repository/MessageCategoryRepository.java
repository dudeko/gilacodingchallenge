package com.gilasw.codingchallenge.repository;

import com.gilasw.codingchallenge.model.MessageCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageCategoryRepository extends MongoRepository<MessageCategory, String> {

    boolean existsMessageCategoryByNameIgnoreCase(String category);
}
