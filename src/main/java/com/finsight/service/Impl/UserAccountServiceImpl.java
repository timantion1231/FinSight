package com.finsight.service.Impl;

import com.finsight.DTO.request.FullUserAccountDTO;
import com.finsight.DTO.response.ResponseFullUserAccountDTO;
import com.finsight.DTO.response.UserAccountDTO;
import com.finsight.entity.Account;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.BankRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    @Autowired
    public UserAccountServiceImpl(AccountRepository accountRepository,
                                  UserRepository userRepository,
                                  BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public ArrayList<UserAccountDTO> getAllAccounts(int userId) {
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findByUserId(userId);
        ArrayList<UserAccountDTO> dtos = new ArrayList<>();
        for (Account account : accounts) {
            dtos.add(mapAccountToDTO(account));
        }
        return dtos;
    }

    private ResponseFullUserAccountDTO mapResponseAccountToDTO(Account account) {
        ResponseFullUserAccountDTO dto = new ResponseFullUserAccountDTO();
        dto.setId(account.getId());
        dto.setUserId(account.getUser().getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBankId(account.getBank().getId());
        return dto;
    }

    private UserAccountDTO mapAccountToDTO(Account account) {
        UserAccountDTO dto = new UserAccountDTO();
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBankId(account.getBank().getId());
        return dto;
    }

    @Override
    public ResponseFullUserAccountDTO getAccount(int id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        return mapResponseAccountToDTO(account);
    }

    @Override
    public UserAccountDTO createAccount(FullUserAccountDTO account) {
        if (account.getAccountNumber() == null || account.getUserId() == 0 || account.getBankId() == 0) {
            throw new IllegalArgumentException("All fields are required");
        }
        Account newAccount = new Account();
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setUser(userRepository.findById(account.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + account.getUserId())));
        newAccount.setBank(bankRepository.findById(account.getBankId()).orElseThrow(() ->
                new ResourceNotFoundException("Bank not found with id: " + account.getBankId())));
        accountRepository.save(newAccount);

        return mapAccountToDTO(newAccount);

    }

    @Override
    public ResponseFullUserAccountDTO editAccount(int id, FullUserAccountDTO account) {
        if (account.getAccountNumber() == null || account.getUserId() == 0 || account.getBankId() == 0) {
            throw new IllegalArgumentException("All fields are required");
        }
        Account newAccount = new Account();
        newAccount.setId(id);
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setUser(userRepository.findById(account.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + account.getUserId())));
        newAccount.setBank(bankRepository.findById(account.getBankId()).orElseThrow(() ->
                new ResourceNotFoundException("Bank not found with id: " + account.getBankId())));
        accountRepository.save(newAccount);
        return mapResponseAccountToDTO(newAccount);
    }

    @Override
    public String deleteAccount(int id) {
        accountRepository.deleteById(id);
        return "Account removed successfully";
    }
}
