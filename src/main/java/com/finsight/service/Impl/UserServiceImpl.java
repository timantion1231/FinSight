package com.finsight.service.Impl;

import com.finsight.DTO.ReportDTO;
import com.finsight.DTO.UserDTO;
import com.finsight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserServiceImpl() {

    }

    @Override
    public UserDTO getBaseUserProfile(int id) {//обращение к бд и тд
        return new UserDTO().randomFill().getBaseUser();
    }

    @Override
    public UserDTO editUserProfile(UserDTO user) {
        return user;
    }


    @Override
    public ReportDTO getReport(int userId) {
        return new ReportDTO();
    }

    @Override
    public String changePassword(String oldPass, String newPass, int userId) {
        return "Password changed successfully";
    }
}
