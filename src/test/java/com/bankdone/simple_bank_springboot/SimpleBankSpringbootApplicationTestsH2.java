package com.bankdone.simple_bank_springboot;

import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bankdone.simple_bank_springboot.entity.Manager;

import java.time.LocalDate;

@SpringBootTest
class SimpleBankSpringbootApplicationTestsH2 {

    @Autowired
    private ManagerRepository managerRepository;
//    @BeforeAll
//    public static void buildSessionFactory() {
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Manager.class)
//                .buildSessionFactory();
//
//    }

    @Test
    void checkH2() {

        Manager manager = Manager.builder()
                .firstName("IIsak")
                .lastName("Malka")
                .status(ManagerStatus.ACTIVE)
                .createdAt(LocalDate.of(2023, 07, 15).atStartOfDay())
                .build();
        Manager save = managerRepository.save(manager);

        System.out.println(save);

        Manager manager1 = Manager.builder()
                .firstName("Abram")
                .lastName("Ierus")
                .status(ManagerStatus.ACTIVE)
                .createdAt(LocalDate.of(2023, 07, 16).atStartOfDay())
                .build();
        Manager save1 = managerRepository.save(manager1);

        System.out.println(save1);


    }

}
