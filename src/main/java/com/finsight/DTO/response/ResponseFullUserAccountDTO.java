package com.finsight.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFullUserAccountDTO {
    private int id;
    private String accountNumber;
    private int bankId;
    private int userId;
}
