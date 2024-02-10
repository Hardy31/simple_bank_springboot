package com.bankdone.simple_bank_springboot.dto;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@Value
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ClientDTO {
    String id;
    String status;
    String taxCode ;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    LocalDateTime updatedAt;
//    String managerId;
    ManagerDTO managerDTO;

}
