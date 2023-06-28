package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.MessageCategory;

import java.util.List;

public interface IMessageCategoryService {

    List<MessageCategory> findAll();

    boolean doesCategoryExist(String category);
}
