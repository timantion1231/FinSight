package com.finsight.repository;

import com.finsight.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {

    Optional<Counterparty> findById(int id); // Получение контрагента по ID

    List<Counterparty> findByUserId(int userId); // Получение контрагента по ID пользователя


    void deleteById(int id); // Удаление контрагента по ID

}