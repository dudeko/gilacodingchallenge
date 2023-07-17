package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.MessageCategory;

import java.util.List;

public interface IMessageCategoryService {

    boolean doesNotExist(String category);

    List<MessageCategory> findAll();
}
