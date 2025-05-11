package com.finsight.service;

import com.finsight.DTO.request.UserUpdateDTO;
import com.finsight.DTO.response.FullUserClientDTO;
import com.finsight.DTO.response.ReportDTO;

public interface UserService {
    FullUserClientDTO getBaseUserProfile(int id);

    FullUserClientDTO editUserProfile(int id, UserUpdateDTO user);

    ReportDTO getReport(int userId);


}
