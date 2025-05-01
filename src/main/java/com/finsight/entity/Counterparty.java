package com.finsight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(name = "counterparties")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id", nullable = false)
    private EntityType entityType;

    @NotNull
    @Column(name = "tin", nullable = false, unique = true, length = 12)
    private String tin;

    @NotNull
    @Column(name = "accountNumber", nullable = false, unique = true, length = 50)
    private String accountNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
