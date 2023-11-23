package com.bankdone.simple_bank_springboot.entity.enums;

/**
 * <h1> Варианты состояния Соглашения</h1>
 * Данный энум будет использован класс AgreementStatus для
 * определения состояния  конктретного соглашения
 * <b>Примечание:
 *   ACTIVE      - активный
 *   EXPIRED     - срок действия истек
 *   TERMINATED  - прекращен
 *   PENDING     - ожидает рассмотрения
 *   NOT_ACTIVE  - неактивный
 *   RENEWED     - обновленный
 *   AMENDED     - изменен
 *   LAPSED      - устаревший
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-22
 */
public enum AgreementStatus {
    ACTIVE,
    EXPIRED,
    TERMINATED,
    PENDING,
    NOT_ACTIVE,
    RENEWED,
    AMENDED,
    LAPSED
}
