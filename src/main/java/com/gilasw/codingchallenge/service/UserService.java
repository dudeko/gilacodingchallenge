package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gilasw.codingchallenge.model.NotificationType.*;
import static com.gilasw.codingchallenge.model.User.create;
import static com.gilasw.codingchallenge.service.MessageCategoryService.*;
import static java.util.List.of;

@Service
public class UserService implements IUserService {

    private List<User> getMockedUsers() {
        return of(
                create().id(1L).name("Olivia").email("olivia@mail.com").phoneNumber("1234-5678").subscribed(of(SPORTS)).channels(of(SMS, EMAIL)),
                create().id(2L).name("Josh").email("josh@mail.com").phoneNumber("2222-2222").subscribed(of(FINANCE, FILMS)).channels(of(PUSH)),
                create().id(3L).name("Sarah").email("sarah@mail.com").phoneNumber("9999-5555").subscribed(of(FINANCE, FILMS, SPORTS)).channels(of()),
                create().id(4L).name("Danny").email("danny@mail.com").phoneNumber("4444-6565").subscribed(of()).channels(of(PUSH, SMS, EMAIL)),
                create().id(5L).name("Jane").email("jane@mail.com").phoneNumber("4321-8888").subscribed(of(FINANCE, FILMS, SPORTS)).channels(of(PUSH, SMS, EMAIL))
        );
    }

    public List<User> findAll() {
        return getMockedUsers();
    }

    @Override
    public List<User> findWithCategory(String categoryName) {
        return getMockedUsers().stream().filter(user -> user.hasCategory(categoryName)).toList();
    }
}
