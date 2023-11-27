package com.bankdone.simple_bank_springboot.entity;

import com.bankdone.simple_bank_springboot.entity.enums.AgreementStatus;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Класс  Agreement (Cjukfitybt)Entity
 * Данный клас является представлением обекта в БД.
 * <p>
 * id               - идентификатор БД
 * interestRate     - процентная ставка (ниже ставки рефинансирования)
 * status           - статус как всегда  подробности AccountStatus (активный, не активный, в разработке, отладке ... )
 * sum              - сумма  по соглашению (не совсем понятна логика!)
 * createdAt       - Локальная дата создания.
 * updatedAt       - Локальная дата  изменения
 * product         = продукт
 * account         - счет
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-27
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "agreement", schema = "public")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AgreementStatus status;

    @Column(name = "sum")
    private double sum;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
