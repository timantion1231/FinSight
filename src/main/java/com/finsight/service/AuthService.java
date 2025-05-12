package com.finsight.service;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.exceptions.EmailAlreadyExistsException;

public interface AuthService {
    void register(RegisterUserDTO registerUserDTO);
}
