package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;

import java.time.LocalDateTime;

public class CreatorFakeEntity {
    public static Manager getFakeManager(Long id){
        Long i = id;
        Manager fakeManager = new Manager().builder()
                .id(i)
                .firstName("NameF" + i)
                .lastName("SurnameF" +i)
                .status(ManagerStatus.valueOf("ACTIVE"))
                .createdAt(LocalDateTime.now())
                .build();
        return fakeManager;
    }
}
