package com.bankdone.simple_bank_springboot.util.dataCSV;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ManagerStr {
    @Id
    private int id;
    private String createdAt;
    private String firstName;
    private String lastName;
    private String status;
    private String updatedAt;

    public void setId(Long id) {
        this.id = Math.toIntExact(id);
    }

    public Long getId() {
        return (long) id;
    }
}
