package com.finsight.controller;

import com.finsight.DTO.AccountDTO;
import com.finsight.DTO.ReportDTO;
import com.finsight.DTO.TransactionDTO;
import com.finsight.DTO.UserDTO;
import com.finsight.service.TransactionService;
import com.finsight.service.UserAccountService;
import com.finsight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    //позже заменить ответ на responseEntity
    private final UserService userService;
    private final TransactionService transactionService;
    private final UserAccountService userAccountService;

    @Autowired
    public UserController(UserService userService, TransactionService transactionService,
                          UserAccountService userAccountService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.userAccountService = userAccountService;
    }
// все requestParam заменить на pathVariable
    @GetMapping("/profile/{id}")
    public UserDTO getProfile(@PathVariable int id) {//DTO изменить так же путь при авторизации (убрать )
        return userService.getBaseUserProfile(id);
    }

    @PutMapping("/profile/")
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userService.editUserProfile(user);
    }

    @GetMapping("/transactions")
    public ArrayList<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/transactions")
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/transactions/")
    public TransactionDTO updateTransaction(@RequestBody TransactionDTO transaction) {
        return transactionService.editTransaction(transaction);
    }

    @DeleteMapping("/transactions/")
    public String deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

    @GetMapping("/accounts")
    public ArrayList<AccountDTO> getAllAccounts() {
        return userAccountService.getAllAccounts();
    }

    @PostMapping("/accounts")
    public AccountDTO createAccount(@RequestBody AccountDTO account) {
        return userAccountService.createAccount(account);
    }

    @DeleteMapping("/accounts/")
    public String deleteAccount(@PathVariable int id) {
        return userAccountService.deleteAccount(id);
    }

    // подумать насчет счетов получателя
    @GetMapping("/reports")
    public ReportDTO getReport(@PathVariable int userId) {
        return userService.getReport(userId);
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