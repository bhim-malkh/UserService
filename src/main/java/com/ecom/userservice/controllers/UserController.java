package com.ecom.userservice.controllers;

import com.ecom.userservice.dtos.UserDTO;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.exception.UserNotFoundException;
import com.ecom.userservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<User> addNewUser(@RequestBody UserDTO userDTO){

        return new ResponseEntity<>(userService.addNewUser(userDTO.getUserFromDTO()), HttpStatus.CREATED);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<User> updateExistingUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateExistingUser(id, userDTO.getUserFromDTO()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> replaceExistingUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return new ResponseEntity<>(userService.replaceExistingUser(id, userDTO.getUserFromDTO()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
