package com.finsight.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_counterparties")
public class UserCounterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // к этой таблице не ссылается ни одна таблица

}
