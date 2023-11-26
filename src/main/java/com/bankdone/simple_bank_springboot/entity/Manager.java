package com.bankdone.simple_bank_springboot.entity;

import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс  ManagerEntity
 * Данный клас является представлением обекта в БД.
 *
 * @Data, @AllArgsConstructor, @NoArgsConstructor, @Builder являются аннотациями Lombock
 *
 * @Entity - эта аннотация помечает класс как сущность JPA, указывая, что он соответствует таблице в базе данных.
 *
 * @Table(name = "manager", schema = "public")  - эта аннотация привязывает обект "ManagerEntity"
 * к таблицы базы данных "manager", с которой сопоставляется сущность.
 *
 * @Id: эта аннотация используется для обозначения поля как первичного ключа сущности.
 *
 * @GeneratedValue(strategy = GenerationType.IDENTITY) - Эта аннотация настраивает стратегию генерации поля
 * первичного ключа. strategy = GenerationType.IDENTITY  - генерацию ключа берет на себя PostgreSQL
 *
 * @Column(name = "id") - эта аннотация используется для указания сопоставления между полем и соответствующим столбцом.
 * в данном случае  поле "private long id" сопостовляется с столбцом "id "  таблици " manager"
 *
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-11-09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "manager", schema = "public")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ManagerStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
