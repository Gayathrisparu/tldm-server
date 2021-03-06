package com.stackroute.tldm.service;

import java.util.List;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.User;

public interface UserService {
    User registerUser(User user) throws UserAlreadyExistsException;

    User updateUser(String userId, User user) throws UserNotFoundException;

    boolean deleteUser(String userId) throws UserNotFoundException;

    User getUserById(String userId) throws UserNotFoundException;

    User getUserByUserName(String userName) throws UserNotFoundException;

    List<User> getAllUsers();
}
