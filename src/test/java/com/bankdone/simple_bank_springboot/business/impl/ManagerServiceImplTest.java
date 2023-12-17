package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

class ManagerServiceImplTest {

    @Mock
    private ManagerRepository managerRepository;

    @Test
    void testGetManagerById() {

        System.out.println(" тест !!!!!!!!!!!!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDate.parse("1998-07-07", formatter).atStartOfDay();
        System.out.println(dateTime);
        System.out.println("________________________________");


        ManagerService managerService = new ManagerServiceImpl(managerRepository);

        Manager managerTemplate = Manager.builder()
                .id(2l)
                .firstName("Haim")
                .lastName("Mizrahim")
                .status(ManagerStatus.ACTIVE)
                .createdAt(dateTime)
                .updatedAt(null)
                .build();
        System.out.println(managerTemplate);


        when(managerRepository.findById(2l) ).thenReturn(Optional.of(managerTemplate));
        Manager managerResult = managerService.getManagerById(2l).get();
        System.out.println("________________");
        System.out.println(managerResult);

        verify(managerRepository).findById(2l);

        compareEntity(managerTemplate, managerResult);


    }

    private void compareEntity(Manager managerTemplate, Manager managerResult) {
        assertAll(
                () -> assertEquals(managerTemplate.getId(), managerResult.getId()),
                () -> assertEquals(managerTemplate.getFirstName(), managerResult.getFirstName()),
                () -> assertEquals(managerTemplate.getLastName(), managerResult.getLastName()),
                () -> assertEquals(managerTemplate.getStatus(), managerResult.getStatus()),
                () -> assertEquals(managerTemplate.getCreatedAt(), managerResult.getCreatedAt()),
                () -> assertEquals(managerTemplate.getUpdatedAt(), managerResult.getUpdatedAt())

        );
    }

}