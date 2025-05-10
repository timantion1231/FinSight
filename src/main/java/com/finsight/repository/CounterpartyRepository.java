package com.finsight.repository;

import com.finsight.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {
    //List<Counterparty> findAll(); // Получение всех контрагентов
    Counterparty findById(int id); // Получение контрагента по ID
}