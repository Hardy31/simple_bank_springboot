package com.bankdone.simple_bank_springboot.util.dataCSV.izy;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerString {
    private int id;
    private String createdAt;
    private String firstName;
    private String lastName;
    private String status;
    private String updatedAt;

}
