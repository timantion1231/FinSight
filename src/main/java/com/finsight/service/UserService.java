package com.finsight.service;

import com.finsight.DTO.ReportDTO;
import com.finsight.DTO.UserDTO;

public interface UserService {
    public UserDTO getBaseUserProfile(int id);

    public UserDTO editUserProfile(UserDTO user);

    public ReportDTO getReport(int userId);

    public String changePassword(String oldPass, String newPass, int userId);

}
