package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.model.User;

import java.util.List;

public interface IUserService {

    List<User> findWithCategory(String category);
}
