package com.finsight.service.Impl;

import com.finsight.DTO.request.UserUpdateDTO;
import com.finsight.DTO.response.FullUserAccountDTO;
import com.finsight.DTO.response.FullUserClientDTO;
import com.finsight.DTO.response.ReportDTO;
import com.finsight.entity.Account;
import com.finsight.entity.User;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public FullUserClientDTO getBaseUserProfile(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); // Updated exception
        FullUserClientDTO dto = new FullUserClientDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setTin(user.getTin());
        dto.setAccounts(getAllAccounts(id));
        return dto;
    }

    @Override
    public FullUserClientDTO editUserProfile(int id, UserUpdateDTO user) {
        if (user.getEmail() == null || user.getName() == null || user.getPhoneNumber() == null || user.getTin() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); // Updated exception
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhoneNumber());
        existingUser.setTin(user.getTin());
        userRepository.updateUserProfile(existingUser);
        return getBaseUserProfile(existingUser.getId());
    }

    @Override
    public ReportDTO getReport(int userId) {
        return new ReportDTO();
    }

    private ArrayList<FullUserAccountDTO> getAllAccounts(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); // Updated exception
        List<Account> accounts = accountRepository.findByUserId(user.getId());
        ArrayList<FullUserAccountDTO> userAccounts = new ArrayList<>();
        for (Account account : accounts) {
            FullUserAccountDTO accountDto = new FullUserAccountDTO();
            accountDto.setId(account.getId());
            accountDto.setAccountNumber(account.getAccountNumber());
            accountDto.setBankId(account.getBank().getId());
            userAccounts.add(accountDto);
        }
        return userAccounts;
    }
}
