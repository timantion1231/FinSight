package com.finsight.service;

import com.finsight.DTO.request.UserAccountDTO;
import com.finsight.DTO.response.FullUserAccountDTO;

import java.util.ArrayList;

public interface UserAccountService {
    ArrayList<FullUserAccountDTO> getAllAccounts(int userId);

    FullUserAccountDTO getAccount(int id);

    FullUserAccountDTO createAccount(UserAccountDTO account);

    FullUserAccountDTO editAccount(int id, UserAccountDTO account);

    String deleteAccount(int id);
}
