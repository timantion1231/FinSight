package com.finsight.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String tin;
    private String accountNumber;
    private int bankId;
}