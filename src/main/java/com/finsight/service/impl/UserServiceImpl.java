package com.finsight.service.impl;

import com.finsight.DTO.request.UserUpdateDTO;
import com.finsight.DTO.response.FullUserClientDTO;
import com.finsight.DTO.response.ReportDTO;
import com.finsight.entity.User;
import com.finsight.repository.UserRepository;
import com.finsight.service.UserService;
import com.finsight.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FullUserClientDTO getBaseUserProfile(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        FullUserClientDTO dto = new FullUserClientDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhone());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public FullUserClientDTO editUserProfile(int id, UserUpdateDTO user) {
        if (user.getEmail() == null || user.getName() == null || user.getPhoneNumber() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhoneNumber());
        userRepository.save(existingUser);
        return getBaseUserProfile(existingUser.getId());
    }

    @Override
    public ReportDTO getReport(int userId) {
        return new ReportDTO();
    }
}
