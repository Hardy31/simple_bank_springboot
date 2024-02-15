package com.bankdone.simple_bank_springboot.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionListDTO {
    List<TransactionDTO> transactionDTOList;
}
