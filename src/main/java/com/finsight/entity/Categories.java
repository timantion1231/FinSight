package com.finsight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "entity_type_id", nullable = false)
    private EntityType entityType;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    @NotNull
    private String name;

}
