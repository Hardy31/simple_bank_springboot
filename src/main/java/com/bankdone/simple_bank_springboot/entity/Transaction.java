package com.bankdone.simple_bank_springboot.entity;

import com.bankdone.simple_bank_springboot.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс  Transaction Entity
 * Данный клас является представлением обекта в БД.
 * <p>
 * id       - идентификатор БД
 * type     - тип счета смотри TransactionType (
 * пDEPOSIT ,   - Депозит <br>
 * WITHDRAWAL,  - Снятие <br>
 * TRANSFER,    - Перевод <br>
 * PAYMENT      - Оплата счетов <br>
 * ATM          - Операция через банкомат <br>
 * POS,         - транзакция, в ходе которой клиент использует дебетовую <br>
 * или кредитную карту для приобретения товаров или услуг у продавца<br>
 * WIRE_TRANSFER - Банковский перевод..)<br>.
 * ammount      - колличество (суииа транзакции, перевода)
 * description  - описание
 * debitAccount - дебетовый счет
 * creditAccount- кредитный счет
 * createdAt    - Локальная дата создания.
 *
 * @автор: alex
 * @от :    05.12.2023
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

//    @Column(name = "debit_account_id")
//    private Long debitAccount;

//    @Column(name = "credit_account_id")
//    private Long creditAccount;

    @ManyToOne
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    private Account debitAccount;

    @ManyToOne
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    private Account creditAccount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
