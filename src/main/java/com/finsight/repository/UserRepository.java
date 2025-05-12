package com.finsight.repository;

import com.finsight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Получение пользователя по ID
    Optional<User> findById(int id);

    // Получение пользователя по email — нужно для авторизации
    Optional<User> findByEmail(String email);

    // Проверка, существует ли пользователь с таким email
    boolean existsByEmail(String email);

    // Обновление профиля пользователя
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :#{#user.email}, u.name = :#{#user.name}, " +
            "u.phone = :#{#user.phone}, u.tin = :#{#user.tin} WHERE u.id = :#{#user.id}")
    void updateUserProfile(User user);
}
