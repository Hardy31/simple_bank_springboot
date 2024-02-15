package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.business.TransactionService;
import com.bankdone.simple_bank_springboot.business.exeption.*;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.data_access.TransactionRepository;
import com.bankdone.simple_bank_springboot.dto.TransactionCreateDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionListDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Transaction;
import com.bankdone.simple_bank_springboot.entity.enums.TransactionType;
import com.bankdone.simple_bank_springboot.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
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
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final TransactionMapper transactionMapper;
    /**
     * getAllTransaction() этот метод возвращает список всех транзакций
     * @return TransactionListDTO
     *
     */
    @Override
    public TransactionListDTO getAllTransaction() {

        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDTO> transactionDTOList = transactionMapper.convertToTransactionDTOList(transactionList);
            return new TransactionListDTO(transactionDTOList);
    }

    /**
     * Optional<Transaction> getTransactionById(Long id) этот метод возвращает
     * транзакцию по ее id
     * @param id Long
     * @return Optional<Transaction>
     *     TODO: реализовать
     */
    @Override
    public TransactionDTO getTransactionById(Long id) {
        return  transactionMapper.convertToDTO(transactionRepository.findById(id).orElseThrow(
                ()-> new TransactionNotFoundExceptio(ErrorMessage.TRANSACTION_NOT_FOUND)
        ));
    }

    /**
     * createTransaction(Transaction transaction) этот метод создает тронзакцию при услловии
     * если достаточно средст вна счете и коды валют софподают.
     * @param transactionDTO
     * @return Transaction
     */
    @Override
    @Cacheable ("Transactions")
    public TransactionDTO createTransaction(TransactionCreateDTO transactionDTO) {
        if (Double.parseDouble(transactionDTO.getAmount()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

//        Временно закоментировал
        Account debitAccount = accountRepository.findById(Long.parseLong(transactionDTO.getDebitAccountId())).orElseThrow(
                ()-> new TransactionNotFoundExceptio(ErrorMessage.TRANSACTION_NOT_FOUND)
        );
        Account creditAccount = accountRepository.findById(Long.parseLong(transactionDTO.getCreditAccountId())).orElseThrow(
                ()-> new TransactionNotFoundExceptio(ErrorMessage.TRANSACTION_NOT_FOUND)
        );
        Transaction transactionCreate = Transaction.builder()
                .type(TransactionType.valueOf(transactionDTO.getType()))
                .amount(Double.valueOf(transactionDTO.getAmount()))
                .description(transactionDTO.getDescription())
                .createdAt(LocalDateTime.now())
                .debitAccount(debitAccount)
                .creditAccount(creditAccount)
                .build();;

        if((debitAccount.getCode().equals(creditAccount.getCode()) & (Double.valueOf(creditAccount.getBalance())> Double.valueOf(transactionDTO.getAmount())))){
           creditAccount.setBalance( Double.valueOf(creditAccount.getBalance()) - Double.valueOf(transactionDTO.getAmount()));
           debitAccount.setBalance(Double.valueOf(debitAccount.getBalance())+ Double.valueOf(transactionDTO.getAmount()));
           log.info("TransactionServiceImpl - createTransaction : {}", transactionCreate);
        } else if (Double.valueOf(creditAccount.getBalance())< Double.valueOf(transactionDTO.getAmount())) {
            log.warn("TransactionServiceImpl - createTransaction : на счете не достаточно средств ");
            throw  new NegativeBalanceThrowException("На счете не достаточно средств");
        } else if (!creditAccount.getCode().equals(debitAccount.getCode())) {
            log.warn("TransactionServiceImpl - createTransaction :  Перевод возможен между счетами с одинаковым кодом валют ");
            throw  new CodeCurrencyNotMatchingException("Перевод возможен между счетами с одинаковым кодом валют");
        }
        return transactionMapper.convertToDTO(transactionRepository.save(transactionCreate));


    }
}
