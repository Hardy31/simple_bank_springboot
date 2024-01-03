package com.bankdone.simple_bank_springboot.dto;

import com.bankdone.simple_bank_springboot.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @
 *
 * @автор: alex
 * @от :    05.12.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionDTO {
    TransactionType type;
    Double amount;
    String description;
    Long debitAccount;
    Long creditAccount;
    private LocalDateTime createdAt;
}
