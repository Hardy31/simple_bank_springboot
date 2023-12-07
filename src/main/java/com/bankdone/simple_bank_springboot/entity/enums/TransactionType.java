package com.bankdone.simple_bank_springboot.entity.enums;

/**
 *<h1> Варианты состояния Трнзакций</h1>
 * Данный энум будет использован класс ManagerTransaction для
 * определения типа проводимой операции по счету
 * <br>Примечание:<br>
 *
 *     DEPOSIT ,     - Депозит <br>
 *     WITHDRAWAL,  - Снятие <br>
 *     TRANSFER,    - Перевод <br>
 *     PAYMENT      - Оплата счетов <br>
 *     ATM          - Операция через банкомат <br>
 *     POS,         - транзакция, в ходе которой клиент использует дебетовую <br>
 *                    или кредитную карту для приобретения товаров или услуг у продавца<br>
 *     WIRE_TRANSFER - Банковский перевод<br>
 *
 *
 * @автор: alex
 * @от :    05.12.2023
 */
public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    PAYMENT,
    ATM,
    POS,
    WIRE_TRANSFER
}
