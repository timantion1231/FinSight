package com.finsight.controller;

import com.finsight.DTO.request.*;
import com.finsight.DTO.request.FullUserAccountDTO;
import com.finsight.DTO.response.*;
import com.finsight.service.CounterpartyService;
import com.finsight.service.TransactionService;
import com.finsight.service.UserAccountService;
import com.finsight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    //позже заменить ответ на responseEntity
    private final UserService userService;
    private final TransactionService transactionService;
    private final UserAccountService userAccountService;
    private final CounterpartyService counterpartyService;

    @Autowired
    public UserController(UserService userService, TransactionService transactionService,
                          UserAccountService userAccountService,
                          CounterpartyService counterpartyService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.userAccountService = userAccountService;
        this.counterpartyService = counterpartyService;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<FullUserClientDTO> getProfile(@PathVariable int id) {
        return ResponseEntity.ok(userService.getBaseUserProfile(id));
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<FullUserClientDTO> updateUser(@PathVariable int id,
                                                        @RequestBody UserUpdateDTO user) {
        return ResponseEntity.ok(userService.editUserProfile(id, user));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<ArrayList<FullTransactionDTO>> getAllTransactions(
            @PathVariable int userId) {
        return ResponseEntity.ok(transactionService.getAllTransactions(userId));
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<FullTransactionDTO> getTransaction(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @PostMapping("/transactions")
    public ResponseEntity<FullTransactionDTO> createTransaction(
            @RequestBody NewTransactionDTO transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<FullTransactionDTO> updateTransaction(
            @PathVariable int id, @RequestBody EditTransactionDTO transaction) {
        return ResponseEntity.ok(transactionService.editTransaction(id, transaction));
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.deleteTransaction(id));
    }

    @GetMapping("/accounts/{userId}")
    public ResponseEntity<ArrayList<UserAccountDTO>> getAllAccounts(@PathVariable int userId) {
        return ResponseEntity.ok(userAccountService.getAllAccounts(userId));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<ResponseFullUserAccountDTO> getAccount(@PathVariable int id) {
        return ResponseEntity.ok(userAccountService.getAccount(id));
    }

    @PostMapping("/accounts")
    public ResponseEntity<UserAccountDTO> createAccount(@RequestBody FullUserAccountDTO account) {
        return ResponseEntity.ok(userAccountService.createAccount(account));
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<ResponseFullUserAccountDTO> updateAccount(@PathVariable int id,
                                                                    @RequestBody FullUserAccountDTO account) {
        return ResponseEntity.ok(userAccountService.editAccount(id, account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        return ResponseEntity.ok(userAccountService.deleteAccount(id));
    }

    @GetMapping("/counterparties/{userId}")
    public ResponseEntity<ArrayList<FullCounterpartyDTO>> getAllCounterParties(
            @PathVariable int userId) {
        return ResponseEntity.ok(counterpartyService.getAllCounterparties(userId));
    }

    @PostMapping("/counterparties")
    public ResponseEntity<FullCounterpartyDTO> createCounterparty(
            @RequestBody CounterpartyDTO counterParty) {
        return ResponseEntity.ok(counterpartyService.createCounterparty(counterParty));
    }

    @PutMapping("/counterparties/{id}")
    public ResponseEntity<FullCounterpartyDTO> updateCounterparty(
            @PathVariable int id, @RequestBody CounterpartyDTO counterparty) {
        return ResponseEntity.ok(counterpartyService.updateCounterparty(id, counterparty));
    }

    @DeleteMapping("/counterparties/{id}")
    public ResponseEntity<String> deleteCounterParty(@PathVariable int id) {
        return ResponseEntity.ok(counterpartyService.deleteCounterparty(id));
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<ReportDTO> getReport(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getReport(userId));
    }

    @GetMapping("/reports")
    public ResponseEntity<ByteArrayResource> getExcelReport(
            @RequestParam(required = false) Long senderBankId,
            @RequestParam(required = false) Long counterpartyBankId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(required = false) Integer transactionStatusId,
            @RequestParam(required = false) Integer transactionTypeId,
            @RequestParam(required = false) String counterpartyTin,
            @RequestParam(required = false) BigDecimal amountMin,
            @RequestParam(required = false) BigDecimal amountMax,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "excel") String format
    ) {
        if (!format.equalsIgnoreCase("excel")) {
            return ResponseEntity.badRequest().body(null);
        }

        return transactionService.generateExcelReport(
                senderBankId, counterpartyBankId, dateFrom, dateTo,
                transactionStatusId, transactionTypeId, counterpartyTin,
                amountMin, amountMax, categoryId);
    }
}
