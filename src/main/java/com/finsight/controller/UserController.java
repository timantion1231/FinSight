package com.finsight.controller;

import com.finsight.DTO.AccountDTO;
import com.finsight.DTO.ReportDTO;
import com.finsight.DTO.TransactionDTO;
import com.finsight.DTO.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    //позже заменить ответ на responseEntity

    @GetMapping("/profile/")
    public UserDTO getProfile(@RequestParam int id) {//DTO изменить так же путь при авторизации (убрать )
        return new UserDTO().randomFill().getBaseUser();
    }

    @PutMapping("/profile/")
    public UserDTO updateUser(@RequestParam int id, @RequestBody UserDTO user) {
        return user;
    }

    @GetMapping("/transactions")
    public ArrayList<TransactionDTO> getAllTransactions() {
        ArrayList<TransactionDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add(new TransactionDTO().randomFill().getBaseTransaction());
        return list;
    }

    @PostMapping("/transactions")
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transaction) {
        return transaction;
    }

    @PutMapping("/transactions/")
    public TransactionDTO updateTransaction(@RequestParam int id, @RequestBody TransactionDTO transaction) {
        return new TransactionDTO();
    }

    @DeleteMapping("/transactions/")
    public void deleteTransaction(@RequestParam int id) {

    }

    @GetMapping("/accounts")
    public ArrayList<AccountDTO> getAllAccounts() {
        ArrayList<AccountDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add(new AccountDTO().randomFill().getBaseAccount());
        return new ArrayList<>();
    }

    @PostMapping("/accounts")
    public AccountDTO createAccount(@RequestBody AccountDTO account) {
        return account;
    }

    @DeleteMapping("/accounts/")
    public void deleteAccount(@RequestParam int id) {

    }

    // подумать насчет счетов получателя
    @GetMapping("/reports")
    public ReportDTO getReport() {
        return new ReportDTO();
    }

}
/*
просмотр данных пользователя UserService
Редактирование пользователя UserService
Просмотр транзакций  TransactionService
Создание транзакций TransactionService
Редактирование транзакции TransactionService
Удаление транзакции TransactionService
Просмотр счетов пользователя UserAccountService
Создание счетов пользователя UserAccountService
Удаление счетов пользователя UserAccountService
Просмотр счетов получателя CounterpartyAccountService
Создание счетов получателя CounterpartyAccountService
Удаление счетов получателя CounterpartyAccountService
Выгрузка отчёта UserService
 */