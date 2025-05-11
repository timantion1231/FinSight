package com.finsight.repository;

import com.finsight.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {

    Optional<Categories> findById(int id); //
}
