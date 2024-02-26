package com.bankdone.simple_bank_springboot.util.dataCSV.izy;

import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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

public class ManagH {
    @Id
    private int id;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
//    private LocalDateTime createdAt;
    private String createdAt;
    private String firstName;
    private String lastName;
    private String status;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
//    private LocalDateTime updatedAt;
    private String updatedAt;

    public void setId(Long id) {
        this.id = Math.toIntExact(id);
    }

    public Long getId() {
        return (long) id;
    }
}
