package com.finsight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "users")
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
    private int password;

    @NotNull
    @Column(name = "phone_number", nullable = false, length = 20)
    @Pattern(regexp = "^\\+?[0-9\\s\\-()]{7,20}$",
            message = "Invalid phone number format")
    private String phone;


    @NonNull
    @Column(name = "tin", nullable = false, unique = true, length = 12)
    private String tin;
}
