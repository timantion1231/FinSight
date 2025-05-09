package com.finsight.service.Impl;

import com.finsight.DTO.request.UserAccountDTO;
import com.finsight.DTO.response.FullUserAccountDTO;
import com.finsight.Randomizer;
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
    public ArrayList<FullUserAccountDTO> getAllAccounts() {
        ArrayList<FullUserAccountDTO> accounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            accounts.add(Randomizer.randomize(FullUserAccountDTO.class));
        }
        return accounts;
    }

    @Override
    public FullUserAccountDTO getAccount(int id) {
        return Randomizer.randomize(FullUserAccountDTO.class);
    }

    @Override
    public FullUserAccountDTO createAccount(UserAccountDTO account) {
        return Randomizer.randomize(FullUserAccountDTO.class);
    }

    @Override
    public FullUserAccountDTO editAccount(UserAccountDTO account) {
        return Randomizer.randomize(FullUserAccountDTO.class);
    }

    @Override
    public String deleteAccount(int id) {
        return "Account removed successfully";
    }
}
