package com.bankdone.simple_bank_springboot.entity.enums;

/**
 * <h1> Варианты Типа счета</h1>
 * Данный энум будет использован класс Account для
 * определения типа Счета  конктретного типа Счета
 * <b>Примечание:
 * IRA,                - индивидуальный пенсионный счет
 * TRUST,              - Счет доверительного управления
 * ESCROW,             - депозитный счет
 * FOREIGN_CURRENCY,   - Счет в валюте
 * CREDIT              - Кредитный счет
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-26
 */
public enum AccountType {
  IRA,
  TRUST,
  ESCROW,
  FOREIGN_CURRENCY,
  CREDIT
}
