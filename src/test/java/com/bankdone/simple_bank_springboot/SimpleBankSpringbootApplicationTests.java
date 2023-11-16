package com.bankdone.simple_bank_springboot;

import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class SimpleBankSpringbootApplicationTests {

    @Autowired
    private ManagerRepository managerRepository;


    @Test
    void checkAddManager() {

        Manager manager = Manager.builder()
                .firstName("IIsak")
                .lastName("Malka")
                .status(ManagerStatus.ACTIVE)
                .createdAt(LocalDate.of(2023, 07, 15).atStartOfDay())
                .build();
        Manager save = managerRepository.save(manager);

        System.out.println(save);




    }

}
