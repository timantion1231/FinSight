package com.finsight.service.Impl;

import com.finsight.DTO.AccountDTO;
import com.finsight.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    public UserAccountServiceImpl() {

    }

    @Override
    public ArrayList<AccountDTO> getAllAccounts() {
        ArrayList<AccountDTO> accounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            accounts.add(new AccountDTO().randomFill().getBaseAccount());
        }
        return accounts;
    }

    @Override
    public AccountDTO getAccount(int id) {
        return new AccountDTO().randomFill().getBaseAccount();
    }

    @Override
    public AccountDTO createAccount(AccountDTO account) {
        return new AccountDTO().randomFill().getBaseAccount();
    }

    @Override
    public AccountDTO editAccount(AccountDTO account) {
        return new AccountDTO().randomFill().getBaseAccount();
    }

    @Override
    public String deleteAccount(int id) {
        return "Account removed successfully";
    }
}
