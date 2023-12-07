package com.bankdone.simple_bank_springboot.dto;

import com.bankdone.simple_bank_springboot.entity.enums.TransactionType;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @
 *
 * @автор: alex
 * @от :    05.12.2023
 */
@Data
public class CreateTransactionDTO {
    TransactionType type;
    Double amount;
    String description;
    Long debitAccount;
    Long creditAccount;
    private LocalDateTime createdAt;
}
