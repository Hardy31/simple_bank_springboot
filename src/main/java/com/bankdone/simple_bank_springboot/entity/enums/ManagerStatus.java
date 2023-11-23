package com.bankdone.simple_bank_springboot.entity.enums;

/**
 * <h1> Варианты состояния Менеджера</h1>
 * Данный энум будет использован класс ManagerEntity для
 * определения состояния  конктретного обекта
 * <b>Примечание:
 *     ACTIVE,      - активное (на рабочем месте)
 *     NOT_ACTIVE,  - не активное (на работе но не на рабочем месте)
 *     ON_VACATION, - в отпуске
 *     SICK_LEAVE,  - больничный
 *     BUSINESS_TRIP,- командировка
 *     PENSION,     - на заслуженном отдыхе (для доп выплот )
 *     DISMISSED,   - уволен
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-11-09
 **/

public enum ManagerStatus {
    ACTIVE,
    NOT_ACTIVE,
    ON_VACATION,
    SICK_LEAVE,
    BUSINESS_TRIP,
    PENSION,
    DISMISSED,
}
