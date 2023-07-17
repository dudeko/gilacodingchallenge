package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.MessageCategory;
import com.gilasw.codingchallenge.repository.MessageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageCategoryService implements IMessageCategoryService {

    @Autowired
    private MessageCategoryRepository messageCategoryRepository;

    @Override
    public boolean doesNotExist(String category) {
        return !messageCategoryRepository.existsMessageCategoryByNameIgnoreCase(category);
    }

    @Override
    public List<MessageCategory> findAll() {
        return messageCategoryRepository.findAll();
    }
}
