package com.finsight.service;

import com.finsight.DTO.AccountDTO;

import java.util.ArrayList;

public interface UserAccountService {
    public ArrayList<AccountDTO> getAllAccounts();

    public AccountDTO getAccount(int id);

    public AccountDTO createAccount(AccountDTO account);

    public AccountDTO editAccount(AccountDTO account);

    public String deleteAccount(int id);
}
