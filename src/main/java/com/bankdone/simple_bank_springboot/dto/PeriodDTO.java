package com.bankdone.simple_bank_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Обект слоя DTO
 * преднозначен для передачи данных выбранного периода
 *      с даты
 *      по дату
 *
 * @автор: alex
 * @от :    01.12.2023
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime beforeDate;
}
