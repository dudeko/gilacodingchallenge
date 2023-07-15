package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.repository.MessageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageCategoryService implements IMessageCategoryService {

    @Autowired
    private MessageCategoryRepository messageCategoryRepository;

    public boolean doesNotExist(String category) {
        return !messageCategoryRepository.existsMessageCategoryByNameIgnoreCase(category);
    }
}
