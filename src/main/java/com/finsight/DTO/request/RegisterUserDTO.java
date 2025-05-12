package com.finsight.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private int entityTypeId;
    private String tin;
}