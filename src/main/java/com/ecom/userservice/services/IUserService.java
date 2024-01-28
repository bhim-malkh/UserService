package com.ecom.userservice.services;

import com.ecom.userservice.entity.User;
import com.ecom.userservice.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {

    User getUser(Long id) throws UserNotFoundException;

    List<User> getAllUsers();

    User addNewUser(User user);

    User updateExistingUser(Long id, User user) throws UserNotFoundException;

    User replaceExistingUser(Long id, User user) throws UserNotFoundException;

    User deleteUser(Long id) throws UserNotFoundException;
}
