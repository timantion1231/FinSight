package com.finsight.service;

import com.finsight.DTO.request.UserAccountDTO;
import com.finsight.DTO.response.FullUserAccountDTO;

import java.util.ArrayList;

public interface UserAccountService {
    public ArrayList<FullUserAccountDTO> getAllAccounts(int userId);

    public FullUserAccountDTO getAccount(int id);

    public FullUserAccountDTO createAccount(UserAccountDTO account);

    public FullUserAccountDTO editAccount(int id,UserAccountDTO account);

    public String deleteAccount(int id);
}
