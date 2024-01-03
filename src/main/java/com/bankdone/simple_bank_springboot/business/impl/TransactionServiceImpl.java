package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.business.TransactionService;
import com.bankdone.simple_bank_springboot.business.exeption.CodeCurrencyNotMatchingException;
import com.bankdone.simple_bank_springboot.business.exeption.NegativeBalanceThrowException;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.data_access.TransactionRepository;
import com.bankdone.simple_bank_springboot.dto.CreateTransactionDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @автор: alex
 * @от :    05.12.2023
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    /**
     * getAllTransaction() этот метод возвращает список всех транзакций
     * @return List<Transaction>
     *     TODO: реализовать
     */
    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    /**
     * Optional<Transaction> getTransactionById(Long id) этот метод возвращает
     * транзакцию по ее id
     * @param id Long
     * @return Optional<Transaction>
     *     TODO: реализовать
     */
    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    /**
     * createTransaction(Transaction transaction) этот метод создает тронзакцию при услловии
     * если достаточно средст вна счете и коды валют софподают.
     * @param transactionDTO
     * @return Transaction
     */
    @Override
    @Cacheable ("Transactions")
    public Transaction createTransaction(CreateTransactionDTO transactionDTO) {
        Account debitAccount = accountService.getById(transactionDTO.getDebitAccount());
        Account creditAccount = accountService.getById(transactionDTO.getCreditAccount());
        Transaction transaction = Transaction.builder()
                .type(transactionDTO.getType())
                .amount(transactionDTO.getAmount())
                .description(transactionDTO.getDescription())
                .createdAt(LocalDateTime.now())
                .debitAccount(debitAccount)
                .creditAccount(creditAccount)
                .build();;

        if((debitAccount.getCode().equals(creditAccount.getCode()) & (creditAccount.getBalance()> transactionDTO.getAmount()))){
           creditAccount.setBalance( creditAccount.getBalance() - transactionDTO.getAmount());
           debitAccount.setBalance(debitAccount.getBalance()+ transactionDTO.getAmount());
           log.info("TransactionServiceImpl - createTransaction : {}", transaction);
        } else if (creditAccount.getBalance()< transactionDTO.getAmount()) {
            log.warn("TransactionServiceImpl - createTransaction : на счете не достаточно средств ");
            throw  new NegativeBalanceThrowException("На счете не достаточно средств");
        } else if (!creditAccount.getCode().equals(debitAccount.getCode())) {
            log.warn("TransactionServiceImpl - createTransaction :  Перевод возможен между счетами с одинаковым кодом валют ");
            throw  new CodeCurrencyNotMatchingException("Перевод возможен между счетами с одинаковым кодом валют");
        }

        return transactionRepository.save(transaction);
    }
}
