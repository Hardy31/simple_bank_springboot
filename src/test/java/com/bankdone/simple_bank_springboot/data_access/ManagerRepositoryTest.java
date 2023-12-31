package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.LocalDateTime.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ManagerRepository - Test")
class ManagerRepositoryTest {

//    @Mock
//    private ManagerRepository managerRepository;
//    private Manager managerFake;
//
//    @BeforeEach
//    void presetting() {
//        managerFake = CreatorFakeEntity.getFakeManager(5L);
//        System.out.println(managerFake);
//
////        Optional<Manager> foundManager = managerRepository.findById(managerFake.getId());
////        Manager man  = foundManager.get();
//    }
//
//    @Test
//    void testGetManagerById(Long id) {
//        when(managerRepository.findById(id)).thenReturn(Optional.ofNullable(managerFake));
//        Optional<Manager> foundManager = managerRepository.findById(managerFake.getId());
//        Manager man = foundManager.get();
//        System.out.println(man);
//
//        assertTrue(foundManager.isPresent());
//        assertEquals(managerFake, foundManager.get());
//        verify(managerRepository, times(1)).findById(managerFake.getId());
//    }
//
//    @Test
//    void testData() {
//        String data = "2023-07-14";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDateTime dateTime = LocalDate.parse(data, formatter).atStartOfDay();
//        System.out.println(dateTime);
//    }

}