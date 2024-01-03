package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.dto.CreateTransactionDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.enums.TransactionType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreaterFakeDTO {

    public static CreateTransactionDTO CreateFakeTransactionDTO(){
//        Account debetAccountTemplate = CreatorFakeEntity.getFakeAccount(9L);
//        Account credetAccountTemplate = CreatorFakeEntity.getFakeAccount(8L);
        CreateTransactionDTO createTransactionDTO =  CreateTransactionDTO.builder()
                .type(TransactionType.DEPOSIT)
                .amount(10000.00d)
                .description("description")
                .debitAccount(9L)
                .creditAccount(8L)
                .createdAt(LocalDateTime.now())
                .build();
        return createTransactionDTO;
    }
}
