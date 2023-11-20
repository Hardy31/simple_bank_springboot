package com.bankdone.simple_bank_springboot.entity.enums;


/**
 * <h1> Варианты состояния Продукта</h1>
 * Данный энум будет использован класс ProductEntity для
 * определения состояния  конктретного обекта
 * <b>Примечание:
 *     ACTIVE,          - активное
 *     NOT_ACTIVE,      - не активное
 *     BLOCKED          - клиент заблокировал счет
 *     DECEASED         - Клиент мертв
 *     PENDING          - Клиент проверяется
 *     VIP              - ВИП Клиент
 *     BLACKLISTED      - клиент в черном списке
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-09
 */
public enum ClientStatus {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    DECEASED,
    PENDING,
    VIP,
    BLACKLISTED
}
