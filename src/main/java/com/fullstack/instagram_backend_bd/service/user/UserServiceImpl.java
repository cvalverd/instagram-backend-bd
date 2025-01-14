package com.fullstack.instagram_backend_bd.service.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fullstack.instagram_backend_bd.api.request.UserUpdateRequest;
import com.fullstack.instagram_backend_bd.exceptionhandler.DatabaseTransactionException;
import com.fullstack.instagram_backend_bd.exceptionhandler.ResourceNotFoundException;
import com.fullstack.instagram_backend_bd.model.User;
import com.fullstack.instagram_backend_bd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User userToSave) {
        logger.info("Creando usuario con request: {} - metodo saveUser",userToSave);
        try {
            User savedUser = userRepository.save(userToSave);
            logger.info("Usuario creado satisfactoriamente. User ID: {} - metodo saveUser",savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error creando usuario - metodo saveUser",e);
            throw new DatabaseTransactionException("Error creando usuario",e);
        }
    }
    @Override
    public Optional<User> findUserById(Long id) {
        logger.info("Finding user by ID: {} - method findUserById", id);
        return userRepository.findById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        logger.info("Finding user by username: {} - method findUserByUsername", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        logger.info("Finding user by email: {} - method findUserByEmail", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(Long id, UserUpdateRequest updateRequest) {
        logger.info("Updating user with ID: {} and request: {} - method updateUser", id, updateRequest);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (updateRequest.getUsername() != null) {
            logger.info("Updating username to: {} - method updateUser", updateRequest.getUsername());
            user.setUsername(updateRequest.getUsername());
        }
        if (updateRequest.getPassword() != null) {
            logger.info("Updating password to: {} - method updateUser", updateRequest.getPassword());
            user.setPassword(updateRequest.getPassword());
        }
        if (updateRequest.getEmail() != null) {
            logger.info("Updating email to: {} - method updateUser", updateRequest.getEmail());
            user.setEmail(updateRequest.getEmail());
        }
        if (updateRequest.getBirthdate() != null) {
            logger.info("Updating birthdate to: {} - method updateUser", updateRequest.getBirthdate());
            user.setBirthdate(updateRequest.getBirthdate());
        }

        logger.info("Saving user - method updateUser");
        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully. User ID: {}", updatedUser.getId());
        return updatedUser;
    }

    @Override
    public List<User> findAllUsers() {
        logger.info("Finding all users - method findAllUsers");
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        logger.info("Deleting user by ID: {} - method deleteUserById", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.info("User not found - method deleteUserById");
            throw new ResourceNotFoundException("User not found");
        }
        logger.info("Deleting user - method deleteUserById");
        userRepository.deleteById(id);
        logger.info("User deleted successfully. User ID: {} - method deleteUserById", id);
    }
}
