package com.finsight.repository;

import com.finsight.entity.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntityTypeRepository extends JpaRepository<EntityType, Integer> {
    // Получение типа контрагента по ID
    Optional<EntityType> findById(int id);

}
