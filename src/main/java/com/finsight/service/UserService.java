package com.finsight.service;

import com.finsight.DTO.request.UserUpdateDTO;
import com.finsight.DTO.response.FullUserClientDTO;
import com.finsight.DTO.response.ReportDTO;

public interface UserService {
    public FullUserClientDTO getBaseUserProfile(int id);

    public FullUserClientDTO editUserProfile(UserUpdateDTO user);

    public ReportDTO getReport(int userId);


}
