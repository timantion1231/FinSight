package com.finsight.repository;

import com.finsight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id); // Получение профиля пользователя по ID
}