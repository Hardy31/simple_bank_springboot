package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreatorFakeEntity {

    public static Manager createFakeManager(){
        Manager fakeManager = new Manager().builder()
                .firstName("NameF" )
                .lastName("SurnameF")
                .status(ManagerStatus.valueOf("ACTIVE"))
                .createdAt(LocalDateTime.now())
                .build();
        return fakeManager;
    }
    public static Manager getFakeManager(Long id){
        Manager fakeManager = createFakeManager();
        fakeManager.setId(id);
        return fakeManager;
    }

    public static LocalDateTime getFixedLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fixedLocalDateTime = LocalDate.parse("1998-07-07", formatter).atStartOfDay();
        return  fixedLocalDateTime;
    }
}
