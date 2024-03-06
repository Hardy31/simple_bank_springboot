package com.bankdone.simple_bank_springboot.entity;


import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//import static javax.persistence.CascadeType.*;


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
 * @от   2023-11-19
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client", schema = "public")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name="tax_code")
    private String taxCode ;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    public Client toClient(){
        Client clientEntity = new Client();
        clientEntity.setId(id);
        clientEntity.setFirstName(firstName);
        clientEntity.setLastName(lastName);
        clientEntity.setEmail(email);
        clientEntity.setAddress(address);
        clientEntity.setPhone(phone);
        clientEntity.setCreatedAt(createdAt);
        clientEntity.setUpdatedAt(updatedAt);
        clientEntity.setManager(manager);
        return clientEntity;
    }




}
