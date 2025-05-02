package com.finsight.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private int id;
    private String email;
    private String passwordHash;
    private String name;
    private String phoneNumber;
    private String tin;

    @JsonIgnore
    public UserDTO getBaseUser(){
        return UserDTO.builder()
                .email(this.email)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .tin(this.tin)
                .build();
    }
    @JsonIgnore
    public UserDTO getFullUser(){
        return this;
    }

    public UserDTO randomFill(){
        EnhancedRandom rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        return rnd.nextObject(UserDTO.class);
    }
}
