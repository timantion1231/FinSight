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

    @GetMapping("/profile/{id}")
    public UserDTO getProfile(@PathVariable int id){//DTO изменить так же путь при авторизации (убрать {id})
        return new UserDTO();
    }

    @PutMapping("/profile/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO user){
        return new UserDTO();
    }

    @GetMapping("/transactions")
    public ArrayList<TransactionDTO> getAllTransactions(){
        return new ArrayList<>();
    }

    @PostMapping("/transactions")
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transaction){
        return new TransactionDTO();
    }

    @PutMapping("/transactions/{id}")
    public TransactionDTO updateTransaction(@PathVariable int id, @RequestBody TransactionDTO transaction){
        return new TransactionDTO();
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable int id){

    }

    @GetMapping("/accounts")
    public ArrayList<AccountDTO> getAllAccounts(){
        return new ArrayList<>();
    }

    @PostMapping("/accounts")
    public AccountDTO createAccount(@RequestBody AccountDTO account){
        return new AccountDTO();
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable int id){

    }

   // подумать насчет счетов получателя
    @GetMapping("/reports")
    public ReportDTO getReport(){
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