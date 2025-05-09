package com.finsight.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullUserClientDTO {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private int entityTypeId;
    private String tin;
    private ArrayList<FullUserAccountDTO> accounts;
}