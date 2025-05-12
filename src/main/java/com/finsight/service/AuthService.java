package com.finsight.service;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.entity.User;
import com.finsight.exceptions.EmailAlreadyExistsException;

import java.util.Optional;

public interface AuthService {

    User register(RegisterUserDTO registerUserDTO) throws EmailAlreadyExistsException;

    Optional<User> getUserById(Integer userId);

    User updateUserProfile(User updatedUser);
}
