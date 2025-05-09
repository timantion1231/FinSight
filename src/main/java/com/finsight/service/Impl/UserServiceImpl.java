package com.finsight.service.Impl;

import com.finsight.DTO.request.UserUpdateDTO;
import com.finsight.DTO.response.FullUserAccountDTO;
import com.finsight.DTO.response.FullUserClientDTO;
import com.finsight.DTO.response.ReportDTO;
import com.finsight.Randomizer;
import com.finsight.entity.Account;
import com.finsight.entity.User;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finsight.repository.UserRepository;

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
    public FullUserClientDTO getBaseUserProfile(int id) {//обращение к бд и тд
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        FullUserClientDTO dto = new FullUserClientDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setTin(user.getTin());
        List<Account> accounts = accountRepository.findByUserId(id);
        ArrayList<FullUserAccountDTO> userAccounts = new ArrayList<>();
        for (Account account : accounts) {
            FullUserAccountDTO accountDto = new FullUserAccountDTO();
            accountDto.setId(account.getId());
            accountDto.setAccountNumber(account.getAccountNumber());
            accountDto.setBankId(account.getBank().getId());
            userAccounts.add(accountDto);
        }
        dto.setAccounts(userAccounts);
        return dto;
    }

    @Override
    public FullUserClientDTO editUserProfile(UserUpdateDTO user) {
        return Randomizer.randomize(FullUserClientDTO.class);
    }


    @Override
    public ReportDTO getReport(int userId) {
        return new ReportDTO();
    }

}

