package com.bankdone.simple_bank_springboot.util.dataCSV.izy;

import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Man {

    private Long id;


    private String firstName;


    private String lastName;


    @Enumerated(EnumType.STRING)
    private ManagerStatus status;


//    @JsonSerialize(using = LocalDateTimeSerializer.class)
////    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private LocalDateTime updatedAt;
}
