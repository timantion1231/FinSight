package com.finsight.service.Impl;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;
import com.finsight.entity.Transaction;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.*;
import com.finsight.service.TransactionService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CounterpartyRepository counterpartyRepository;
    private final UserRepository userRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionStatusRepository transactionStatusRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository,
                                  CounterpartyRepository counterpartyRepository,
                                  UserRepository userRepository,
                                  TransactionTypeRepository transactionTypeRepository,
                                  TransactionStatusRepository transactionStatusRepository,
                                  CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.counterpartyRepository = counterpartyRepository;
        this.userRepository = userRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionStatusRepository = transactionStatusRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ArrayList<FullTransactionDTO> getAllTransactions(int userId) {
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionRepository.getAllByUserId(userId);
        ArrayList<FullTransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDTOs.add(mapTransactionToDTO(transaction));
        }
        return transactionDTOs;
    }

    @Override
    public FullTransactionDTO getTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return mapTransactionToDTO(transaction);
    }

    private FullTransactionDTO mapTransactionToDTO(Transaction transaction) {
        FullTransactionDTO dto = new FullTransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDateTime(transaction.getDateTime());
        dto.setTransactionTypeId(transaction.getTransactionType().getId());
        dto.setTransactionStatusId(transaction.getTransactionStatus().getId());
        dto.setComment(transaction.getComment());
        dto.setUserId(transaction.getUser().getId());
        dto.setCounterpartyId(transaction.getCounterparty().getId());
        dto.setUserIsSender(transaction.isUserSender());
        dto.setCategoryId(transaction.getCategories().getId());
        dto.setAccountId(transaction.getAccount().getId());
        return dto;
    }

    @Override
    public FullTransactionDTO editTransaction(int id, EditTransactionDTO transaction) {
        if (transaction.getDateTime() == null || transaction.getTransactionTypeId() == 0 ||
                transaction.getTransactionStatusId() == 0 || transaction.getAmount() == 0 ||
                transaction.getAccountId() == 0 || transaction.getCounterpartyId() == 0 ||
                transaction.getCategoryId() == 0) {
            throw new IllegalArgumentException("All fields are required");
        }
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        existingTransaction.setDateTime(transaction.getDateTime());
        existingTransaction.setTransactionType(transactionTypeRepository.findById(
                transaction.getTransactionTypeId()).orElseThrow(() -> new ResourceNotFoundException(
                "Transaction type not found with id: " + transaction.getTransactionTypeId())));
        existingTransaction.setTransactionStatus(transactionStatusRepository.findById(
                transaction.getTransactionStatusId()).orElseThrow(() -> new ResourceNotFoundException(
                "Transaction status not found with id: " + transaction.getTransactionStatusId())));
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setComment(transaction.getComment());
        existingTransaction.setAccount(accountRepository.findById(
                transaction.getAccountId()).orElseThrow(() -> new ResourceNotFoundException(
                "Account not found with id: " + transaction.getAccountId())));
        existingTransaction.setCounterparty(counterpartyRepository.findById(
                transaction.getCounterpartyId()).orElseThrow(() -> new ResourceNotFoundException(
                "Counterparty not found with id: " + transaction.getCounterpartyId())));
        existingTransaction.setUserSender(transaction.isUserIsSender());
        existingTransaction.setCategories(categoryRepository.findById(
                transaction.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(
                "Category not found with id: " + transaction.getCategoryId())));
        transactionRepository.save(existingTransaction);
        return mapTransactionToDTO(existingTransaction);
    }

    @Override
    public FullTransactionDTO createTransaction(NewTransactionDTO transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setDateTime(transaction.getDateTime());
        newTransaction.setTransactionType(transactionTypeRepository.findById(
                transaction.getTransactionTypeId()).orElseThrow(() -> new ResourceNotFoundException(
                "Transaction type not found with id: " + transaction.getTransactionTypeId())));
        newTransaction.setTransactionStatus(transactionStatusRepository.findById(
                transaction.getTransactionStatusId()).orElseThrow(() -> new ResourceNotFoundException(
                "Transaction status not found with id: " + transaction.getTransactionStatusId())));
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setComment(transaction.getComment());
        newTransaction.setAccount(accountRepository.findById(
                transaction.getAccountId()).orElseThrow(() -> new ResourceNotFoundException(
                "Account not found with id: " + transaction.getAccountId())));
        newTransaction.setCounterparty(counterpartyRepository.findById(
                transaction.getCounterpartyId()).orElseThrow(() -> new ResourceNotFoundException(
                "Counterparty not found with id: " + transaction.getCounterpartyId())));
        newTransaction.setUserSender(transaction.isUserIsSender());
        newTransaction.setCategories(categoryRepository.findById(
                transaction.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(
                "Category not found with id: " + transaction.getCategoryId())));
        newTransaction.setUser(userRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + transaction.getUserId())));
        transactionRepository.save(newTransaction);
        return mapTransactionToDTO(newTransaction);
    }

    @Override
    public String deleteTransaction(int id) {
        transactionRepository.deleteById(id);
        return "Transaction removed successfully";
    }

    @Override
    public ResponseEntity<ByteArrayResource> generateExcelReport(Long senderBankId, Long counterpartyBankId,
                                                                 LocalDate dateFrom, LocalDate dateTo,
                                                                 Integer transactionStatusId, Integer transactionTypeId,
                                                                 String counterpartyTin, BigDecimal amountMin,
                                                                 BigDecimal amountMax, Long categoryId) {

        List<Transaction> transactions = transactionRepository.findAll().stream()
                .filter(t -> senderBankId == null || getSenderBankId(t).equals(senderBankId))
                .filter(t -> counterpartyBankId == null || getReceiverBankId(t).equals(counterpartyBankId))
                .filter(t -> dateFrom == null || !toLocalDate(t.getDateTime()).isBefore(dateFrom))
                .filter(t -> dateTo == null || !toLocalDate(t.getDateTime()).isAfter(dateTo))
                .filter(t -> transactionStatusId == null || t.getTransactionStatus().getId() == transactionStatusId)
                .filter(t -> transactionTypeId == null || t.getTransactionType().getId() == transactionTypeId)
                .filter(t -> counterpartyTin == null || t.getCounterparty().getTin().equals(counterpartyTin))
                .filter(t -> amountMin == null || BigDecimal.valueOf(t.getAmount()).compareTo(amountMin) >= 0)
                .filter(t -> amountMax == null || BigDecimal.valueOf(t.getAmount()).compareTo(amountMax) <= 0)
                .filter(t -> categoryId == null || t.getCategories().getId() == categoryId)
                .collect(Collectors.toList());

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Отчёт");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Дата");
            header.createCell(1).setCellValue("Отправитель");
            header.createCell(2).setCellValue("Получатель");
            header.createCell(3).setCellValue("ИНН контрагента");
            header.createCell(4).setCellValue("Сумма");
            header.createCell(5).setCellValue("Тип");
            header.createCell(6).setCellValue("Статус");

            int rowNum = 1;
            for (Transaction t : transactions) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(toLocalDate(t.getDateTime()).toString());
                row.createCell(1).setCellValue(getSenderName(t));
                row.createCell(2).setCellValue(getReceiverName(t));
                row.createCell(3).setCellValue(t.getCounterparty().getTin());
                row.createCell(4).setCellValue(String.valueOf(t.getAmount()));
                row.createCell(5).setCellValue(t.getTransactionType().getName());
                row.createCell(6).setCellValue(t.getTransactionStatus().getName());
            }

            for (int i = 0; i < 7; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.xlsx")
                    .body(resource);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при генерации Excel-отчета", e);
        }
    }

    private LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private String getSenderName(Transaction t) {
        return t.isUserSender()
                ? t.getAccount().getBank().getName()
                : t.getCounterparty().getBank().getName();
    }

    private String getReceiverName(Transaction t) {
        return t.isUserSender()
                ? t.getCounterparty().getBank().getName()
                : t.getAccount().getBank().getName();
    }

    private Long getSenderBankId(Transaction t) {
        return t.isUserSender()
                ? (long) t.getAccount().getBank().getId()
                : (long) t.getCounterparty().getBank().getId();
    }

    private Long getReceiverBankId(Transaction t) {
        return t.isUserSender()
                ? (long) t.getCounterparty().getBank().getId()
                : (long) t.getAccount().getBank().getId();
    }
}

