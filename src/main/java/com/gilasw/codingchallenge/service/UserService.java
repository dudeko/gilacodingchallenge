package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gilasw.codingchallenge.model.User.create;
import static com.gilasw.codingchallenge.notification.NotificationType.EMAIL;
import static com.gilasw.codingchallenge.notification.NotificationType.PUSH;
import static com.gilasw.codingchallenge.notification.NotificationType.SMS;
import static com.gilasw.codingchallenge.service.MessageCategoryService.FILMS;
import static com.gilasw.codingchallenge.service.MessageCategoryService.FINANCE;
import static com.gilasw.codingchallenge.service.MessageCategoryService.SPORTS;
import static java.util.List.of;

@Service
public class UserService implements IUserService {

    private List<User> getMockedUsers() {
        return of(
                create().id(1L).name("Frank").email("frank@mail.com").phoneNumber("1234-5678").subscribed(of(SPORTS)).channels(of(SMS, EMAIL)),
                create().id(2L).name("Josh").email("josh@mail.com").phoneNumber("2222-2222").subscribed(of(FINANCE, FILMS)).channels(of(PUSH)),
                create().id(3L).name("Simon").email("simon@mail.com").phoneNumber("9999-5555").subscribed(of(FINANCE, FILMS, SPORTS)).channels(of(PUSH, SMS, EMAIL))
        );
    }

    public List<User> findAll() {
        return getMockedUsers();
    }

    @Override
    public List<User> findWithCategory(String category) {
        return getMockedUsers().stream().filter(user -> user.hasCategory(category)).toList();
    }
}
