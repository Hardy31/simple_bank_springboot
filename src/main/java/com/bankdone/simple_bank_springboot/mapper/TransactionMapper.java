package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.TransactionCreateDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionListDTO;
import com.bankdone.simple_bank_springboot.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",  imports = LocalDateTime.class)
public interface TransactionMapper {

    @Mapping(target = "debitAccountId", source = "transaction.debitAccount.id")
    @Mapping(target = "creditAccountId", source = "transaction.creditAccount.id")
    TransactionDTO convertToDTO(Transaction transaction);
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    TransactionDTO createTransactionDTO(TransactionCreateDTO transactionCreateDTO);
    Transaction convertToEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> convertToTransactionDTOList (List<Transaction> TransactionList);


}
