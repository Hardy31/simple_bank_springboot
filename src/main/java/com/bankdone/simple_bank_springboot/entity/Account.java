package com.bankdone.simple_bank_springboot.entity;

import com.bankdone.simple_bank_springboot.entity.enums.AccountStatus;
import com.bankdone.simple_bank_springboot.entity.enums.AccountType;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс  Account Entity
 * Данный клас является представлением обекта в БД.
 *
 * id       - идентификатор БД
 * name     - Номер счета (Номер расчетного счета состоит из 20 цифр
 *          — в них зашифрованы данные о самом адресате и характеристиках счета. )
 * type     - тип счета смотри AccountType (пенсионный, инвестиционный, кредитный, депозитный, зарплатный...)
 * status   - статус как всегда  подробности AccountStatus (активный, не активный, в разработке, отладке ... )
 * balance  - баланс счета (депозитный не модет бфть -, логика кредитного и депозитного должна отгичаться)
 * code     - Код валют по международному стандарту ( RUB   - рубли РФ, USD   - Доллары США, EUR   - Евро)
 * createdAt - Локальная дата создания.
 * updatedAt - Локальная дата  изменения
 * client    - ссылка на владельца данного счета (Клиента) связь Много к одномк ( у Клиента может быть много счетов)
 *
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-11-27
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account", schema = "public")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode code;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


}
