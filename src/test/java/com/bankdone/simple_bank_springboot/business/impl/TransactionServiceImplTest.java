package com.bankdone.simple_bank_springboot.business.impl;


import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.business.TransactionService;
import com.bankdone.simple_bank_springboot.data_access.TransactionRepository;
import com.bankdone.simple_bank_springboot.dto.TransactionCreateDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Transaction;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for TransactionServiceImplTest")
@Slf4j
class TransactionServiceImplTest {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    AccountService accountService;
    Transaction transactionTemplate;

    List<Transaction> transactionTemplairtList;
    Account debetAccountTemplate;
    Account credetAccountTemplate;
//    CreateTransactionDTO createFakeTransactionDTO;
    TransactionCreateDTO createFakeTransactionDTO;
    TransactionService transactionService;

//    @BeforeEach
//    void setUp(){
//        createFakeTransactionDTO =  CreaterFakeDTO.CreateFakeTransactionDTO();
//        transactionTemplate = CreatorFakeEntity.getFakeTrensaction(3L);
//        debetAccountTemplate = CreatorFakeEntity.getFakeAccount(9L);
//        credetAccountTemplate = CreatorFakeEntity.getFakeAccount(8L);
//        transactionService = new TransactionServiceImpl(transactionRepository, accountService);
//        transactionTemplairtList = new ArrayList<>(List.of(transactionTemplate));
//    }
    @Test
    void createTransaction() {
//        when(transactionRepository.save(any())).thenReturn(transactionTemplate);
//        when(accountService.getById(any()))
//                .thenReturn(debetAccountTemplate)       //вернет результат при первом вызове deaccountService.getById()
//                .thenReturn(credetAccountTemplate);     //вернет результат  при повторном вызове deaccountService.getById()
//
//        Transaction result = transactionService.createTransaction(createFakeTransactionDTO);
//        verify(transactionRepository).save(any());
//        assertEquals(transactionTemplate, result);
    }

    @Test
    void getAllTransaction() {
//        when(transactionRepository.findAll()).thenReturn(transactionTemplairtList);
//        List<Transaction> transactionList = transactionService.getAllTransaction();
//        verify(transactionRepository).findAll();
//        assertEquals(transactionTemplairtList,transactionList);
    }

    @Test
    void getTransactionById() {
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transactionTemplate));
//        Transaction result = transactionService.getTransactionById(10L).get();
//        verify(transactionRepository).findById(anyLong());
//        log.info("TransactionServiceImplTest - getTransactionById -transactionTemplate : {}", transactionTemplate);
//        log.info("TransactionServiceImplTest - getTransactionById -result : {}", result);
//        assertEquals(transactionTemplate,result);
    }
}