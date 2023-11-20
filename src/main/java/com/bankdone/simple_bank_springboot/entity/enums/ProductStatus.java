package com.bankdone.simple_bank_springboot.entity.enums;

 /**
  * <h1> Варианты состояния Продукта</h1>
  * Данный энум будет использован класс ProductEntity для
  * определения состояния  конктретного обекта
  * <b>Примечание:
  *     ACTIVE,             - активное (Продукт в работе)
  *     NOT_ACTIVE,         - не активное (на работе но не на рабочем месте)
  *     TEMPORARILY_UNAVAILABLE, - Временно не доступен (пусть будет по техническим причинам)
  *     PENDING_APPROVAL,   - Продукт горовящийся к запуску
  *     PILOT,              - Продукт запушен в пилотное использование в определенном рынке, секторе...
  *     SUSPENDED,          - на заслуженном отдыхе (для доп выплот )
  *
  * @автор Hardy
  * @версия 1.0
  * @от 2023-11-09
  */
public enum ProductStatus {
    ACTIVE,
    TEMPORARILY_UNAVAILABLE,
    PENDING_APPROVAL,
    PILOT,
    SUSPENDED
}
