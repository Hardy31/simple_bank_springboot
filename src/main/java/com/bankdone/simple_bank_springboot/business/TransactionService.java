package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.dto.CreateTransactionDTO;
import com.bankdone.simple_bank_springboot.entity.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @пакет: simple_bank_springboot
 * @автор: alex
 * @от :    05.12.2023
 */
public interface TransactionService {

    List<Transaction> getAllTransaction();

    Optional<Transaction> getTransactionById(Long id);

    Transaction createTransaction(CreateTransactionDTO transaction);

}
