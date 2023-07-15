package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.User;
import com.gilasw.codingchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findWithCategory(String categoryName) {
        return userRepository.findBySubscribedNameIgnoreCase(categoryName);
    }
}
