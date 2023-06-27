package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.MessageCategory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gilasw.codingchallenge.model.MessageCategory.create;

@Service
public class MessageCategoryService implements IMessageCategoryService {

    public static final MessageCategory SPORTS = create().name("Sports");
    public static final MessageCategory FINANCE = create().name("Finance");
    public static final MessageCategory FILMS = create().name("Films");

    private static List<MessageCategory> getMockedMessageCategories() {
        return List.of(SPORTS, FINANCE, FILMS);
    }

    public List<MessageCategory> findAll() {
        return getMockedMessageCategories();
    }
}
