package com.ecom.userservice.services.impl;

import com.ecom.userservice.entity.User;
import com.ecom.userservice.exception.UserNotFoundException;
import com.ecom.userservice.repository.UserRepository;
import com.ecom.userservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByIdAndIsActiveTrue(id);
        return optionalUser.orElseThrow(() -> getUserNotFoundException(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByIsActiveTrue();
    }

    @Override
    public User addNewUser(User user) {
        user.setIsActive(Boolean.TRUE);
        user.setCreatedAt(new Date());
        user.setLastUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User updateExistingUser(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByIdAndIsActiveTrue(id);
        optionalUser.ifPresent(eUser -> {
            if(user.getName() != null)
                eUser.setName(user.getName());
            if(user.getAge() != null)
                eUser.setAge(user.getAge());
            if(user.getEmail() != null)
                eUser.setEmail(user.getEmail());
            if(user.getPhoneNumber() != null)
                eUser.setPhoneNumber(user.getPhoneNumber());
            if(user.getIsActive() != null)
                eUser.setIsActive(user.getIsActive());
            eUser.setLastUpdatedAt(new Date());
            userRepository.save(eUser);
        });
        return optionalUser.orElseThrow(() -> getUserNotFoundException(id));
    }

    @Override
    public User replaceExistingUser(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByIdAndIsActiveTrue(id);
        optionalUser.ifPresent(eUser -> {
            user.setId(id);
            user.setCreatedAt(eUser.getCreatedAt());
            user.setLastUpdatedAt(new Date());
            userRepository.save(user);
        });
        return optionalUser.orElseThrow(() -> getUserNotFoundException(id));
    }

    @Override
    public User deleteUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByIdAndIsActiveTrue(id);
        optionalUser.ifPresent(eUser -> {
            eUser.setIsActive(Boolean.FALSE);
            eUser.setLastUpdatedAt(new Date());
            userRepository.save(eUser);
        });
        return optionalUser.orElseThrow(() -> getUserNotFoundException(id));
    }

    private UserNotFoundException getUserNotFoundException(Long id) {
        return new UserNotFoundException("User with id: " + id + " not found");
    }
}
