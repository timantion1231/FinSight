package com.finsight.service.Impl;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.DTO.request.FullUserAccountDTO;
import com.finsight.entity.User;
import com.finsight.exceptions.EmailAlreadyExistsException;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.BankRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.AuthService;
import com.finsight.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AccountRepository accountRepository,
                           BankRepository bankRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        User user = new User();
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setTin(registerUserDTO.getTin());
        user.setPhone(registerUserDTO.getPhoneNumber());
        user.setName(registerUserDTO.getName());

        userRepository.save(user);
        UserAccountService userAccountService = new UserAccountServiceImpl(
                accountRepository, userRepository, bankRepository);
        userAccountService.createAccount(new FullUserAccountDTO(
                registerUserDTO.getAccountNumber(),
                registerUserDTO.getBankId(),
                user.getId()
        ));
    }
}
