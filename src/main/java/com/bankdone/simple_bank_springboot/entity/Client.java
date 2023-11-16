package com.bankdone.simple_bank_springboot.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "client", schema = "public")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name="status")
    private String status;

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
