package com.finsight.repository;

import com.finsight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id); // Получение профиля пользователя по ID

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :#{#user.email}, u.name = :#{#user.name}," +
            "u.phone = :#{#user.phone}, u.tin = :#{#user.tin} WHERE u.id = :#{#user.id}")
    void updateUserProfile(User user);

    boolean existsByEmail(String email);
}
