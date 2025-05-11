package com.finsight.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;


    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "phone_number", nullable = false, length = 20)
    @Pattern(regexp = "^\\+?[0-9\\s\\-()]{7,20}$",
            message = "Invalid phone number format")
    @JsonProperty("phoneNumber")
    private String phone;


    @NotNull
    @Column(name = "tin", nullable = false, unique = true, length = 12)
    private String tin;
}
