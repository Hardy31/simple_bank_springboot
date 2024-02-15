package com.bankdone.simple_bank_springboot.dto;

import com.bankdone.simple_bank_springboot.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionCreateDTO {
    String type;
    String amount;
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    LocalDateTime createdAt;
    String debitAccountId;
    String creditAccountId;
}
