package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.dto.TransactionCreateDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionListDTO;
import com.bankdone.simple_bank_springboot.entity.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @пакет: simple_bank_springboot
 * @автор: alex
 * @от :    05.12.2023
 */
public interface TransactionService {

    TransactionListDTO getAllTransaction();

    TransactionDTO getTransactionById(Long id);

    TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO);

}
