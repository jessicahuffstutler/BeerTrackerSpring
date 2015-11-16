package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessicahuffstutler on 11/11/15.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    //we have to create a method to find user by name instead of Id.
    User findOneByName(String name);
}
