package com.bankdone.simple_bank_springboot.entity;

import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * Приведенный фрагмент кода определяет класс, который представляет объект в базе данных с помощью
 * аннотации JPA (Java Persistence API).
 * `@Entity`: эта аннотация помечает класс как сущность JPA, указывая, что он соответствует таблице в базе данных.
 * `@Table(name = "manager")`: эта аннотация указывает имя таблицы базы данных, с которой сопоставляется сущность.
 * В этом случае сущность сопоставляется с таблицей «соглашения».
 * `@Getter` и `@Setter` вместо ниж использованна обобщенная анантация  Data: эти аннотации взяты из библиотеки Lombok
 * и автоматически генерируют метод получения. и методы установки полей в классе.
 * `@NoArgsConstructor`: Эта аннотация создает для класса конструктор без аргументов.
 * `@AllArgsConstructor`: эта аннотация создает конструктор с параметрами для всех полей в классе.
 *
 * Применяя ниже перечисленные аннотации к классу, вы определяете его как объект JPA, который отображается
 * в таблицу «счета» в базе данных. Сгенерированные методы получения и установки обеспечивают доступ к полям сущности.
 * и конструкторы позволяют создавать экземпляры сущности с аргументами или без них.
 *
 * @Id: эта аннотация используется для обозначения поля как первичного ключа сущности.
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * Эта аннотация настраивает стратегию генерации поля первичного ключа.
 * @Column: эта аннотация используется для указания сопоставления между полем и соответствующим столбцом.
 * в таблице базы данных. При использовании без каких-либо параметров предполагается, что имя столбца совпадает с именем поля.
 * @Enumerated(EnumType.STRING): эта аннотация используется для указания того, как перечислимый тип сопоставляется с базой данных.
 * В данном случае это указывает на то, что перечисляемый тип должен храниться в базе данных как строка.
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


//    @OneToMany(mappedBy = "manager", cascade = {MERGE, PERSIST, REFRESH}, orphanRemoval = true, fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "manager",  orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Client> clients;


}
