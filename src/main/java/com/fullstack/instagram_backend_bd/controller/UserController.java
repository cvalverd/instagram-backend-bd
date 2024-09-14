package com.fullstack.instagram_backend_bd.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.instagram_backend_bd.api.request.UserUpdateRequest;
import com.fullstack.instagram_backend_bd.service.user.UserService;
import com.fullstack.instagram_backend_bd.model.User;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating a new user with request: {}", user);
        User savedUser = userService.saveUser(user);
        logger.info("User created successfully. User ID: {}", savedUser.getId());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("Getting a user by ID: {}", id);
        Optional<User> user = userService.findUserById(id);
        return user.map(value -> {
            logger.info("User found by ID: {}", id);
            return new ResponseEntity<>(value, HttpStatus.OK);
        })
                .orElseGet(() -> {
                    logger.info("User not found by ID: {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        logger.info("Getting a user by username: {}", username);
        User user = userService.findUserByUsername(username);
        if (user != null) {
            logger.info("User found by username: {}", username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            logger.info("User not found by username: {}", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        logger.info("Getting a user by email: {}", email);
        User user = userService.findUserByEmail(email);
        if (user != null) {
            logger.info("User found by email: {}", email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            logger.info("User not found by email: {}", email);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Getting all users");
        List<User> users = userService.findAllUsers();
        logger.info("Found {} users", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody UserUpdateRequest updateRequest) {
        logger.info("Updating a user with ID: {} and request: {}", id, updateRequest);
        User updatedUser = userService.updateUser(id, updateRequest);
        logger.info("User updated successfully. User ID: {}", updatedUser.getId());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Deleting a user with ID: {}", id);
        userService.deleteUserById(id);
        logger.info("User deleted successfully. User ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
