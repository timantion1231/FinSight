package com.finsight.service.impl;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.entity.User;
import com.finsight.exceptions.EmailAlreadyExistsException;
import com.finsight.repository.UserRepository;
import com.finsight.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterUserDTO registerUserDTO) throws EmailAlreadyExistsException {
        // Проверка уникальности email
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email " + registerUserDTO.getEmail() + " is already registered"
            );
        }

        // Создание нового пользователя
        User user = new User();
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        // Сохранение и возврат пользователя
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional
    public User updateUserProfile(User updatedUser) {
        return userRepository.save(updatedUser);
    }
}
