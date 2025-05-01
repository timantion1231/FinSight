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
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber; // FK с чем? с какой таблицей и каким полем

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type_id", nullable = false)
    private EntityType entityType;

    @NotNull
    @Column(name = "tin", nullable = false, unique = true, length = 12)
    private String tin;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber", nullable = false, unique = true)
    private Account account;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false, unique = true)
    private Bank bank; //какой тип связи и вообще зачем это поле? id банка можно узнать через account

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
