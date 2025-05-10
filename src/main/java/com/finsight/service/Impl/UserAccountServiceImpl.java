package com.finsight.service.Impl;

import com.finsight.DTO.request.UserAccountDTO;
import com.finsight.DTO.response.FullUserAccountDTO;
import com.finsight.Randomizer;
import com.finsight.entity.Account;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserAccountServiceImpl(AccountRepository accountRepository,
                                  UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<FullUserAccountDTO> getAllAccounts(int userId) {
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findByUserId(userId);
        ArrayList<FullUserAccountDTO> dtos = new ArrayList<>();
        for (Account account : accounts) {
            dtos.add(mapAccountToDTO(account));
        }
        return dtos;
    }

    private FullUserAccountDTO mapAccountToDTO(Account account) {
        FullUserAccountDTO dto = new FullUserAccountDTO();
        dto.setId(account.getId());
        dto.setUserId(account.getUser().getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBankId(account.getBank().getId());
        return dto;
    }

    @Override
    public FullUserAccountDTO getAccount(int id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + id));
        return mapAccountToDTO(account);
    }

    @Override
    public FullUserAccountDTO createAccount(UserAccountDTO account) {
        Account newAccount = new Account();
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setUser(userRepository.findById(account.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("User not found with id: " + account.getUserId())));
        newAccount.setBank(accountRepository.findBankById(account.getBankId()).orElseThrow(() ->
                new IllegalArgumentException("Bank not found with id: " + account.getBankId())));
        accountRepository.save(newAccount);
        return mapAccountToDTO(newAccount);

    }

    @Override
    public FullUserAccountDTO editAccount(int id, UserAccountDTO account) {
        FullUserAccountDTO dto = new FullUserAccountDTO();
        Account newAccount = new Account();
        newAccount.setId(id);
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setUser(userRepository.findById(account.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("User not found with id: " + account.getUserId())));
        newAccount.setBank(accountRepository.findBankById(account.getBankId()).orElseThrow(() ->
                new IllegalArgumentException("Bank not found with id: " + account.getBankId())));
        accountRepository.save(newAccount);
        return mapAccountToDTO(newAccount);
    }

    @Override
    public String deleteAccount(int id) {
        accountRepository.deleteById(id);
        return "Account removed successfully";
    }
}
