package com.fullstack.instagram_backend_bd.service.user;

import java.util.List;
import java.util.Optional;

import com.fullstack.instagram_backend_bd.model.User;
import com.fullstack.instagram_backend_bd.api.request.UserUpdateRequest;

public interface UserService {
    User saveUser(User user);
    Optional<User> findUserById(Long id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User updateUser(Long id, UserUpdateRequest updateRequest);
    List<User> findAllUsers();
    void deleteUserById(Long id);
}
