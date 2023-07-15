package com.gilasw.codingchallenge.repository;

import com.gilasw.codingchallenge.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findBySubscribedNameIgnoreCase(String categoryName);

}
