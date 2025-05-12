package com.finsight.service;

import com.finsight.DTO.request.FullUserAccountDTO;
import com.finsight.DTO.response.ResponseFullUserAccountDTO;
import com.finsight.DTO.response.UserAccountDTO;

import java.util.ArrayList;

public interface UserAccountService {
    ArrayList<UserAccountDTO> getAllAccounts(int userId);

    ResponseFullUserAccountDTO getAccount(int id);

    UserAccountDTO createAccount(FullUserAccountDTO account);

    ResponseFullUserAccountDTO editAccount(int id, FullUserAccountDTO account);

    String deleteAccount(int id);
}
