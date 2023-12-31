package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for ManagerServiceImpl")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ManagerServiceImplTest {

    @Mock
    private ManagerRepository managerRepository;

    private ManagerService managerService;
    private Manager managerTemplate;
    private List<Manager> managerList;

    @BeforeEach
    void setUp() {
        managerService = new ManagerServiceImpl(managerRepository);
        managerTemplate = CreatorFakeEntity.getFakeManager(2L);
        managerList = new ArrayList<>(List.of(managerTemplate));
    }

    @Test
    @DisplayName("Positive test. Create manager.")
    void testCreateManager() {
        when(managerRepository.save(any())).thenReturn(managerTemplate);  // Стаббинг: определение поведения, Возвращает ФэйкМенеджер с Id
//        Manager acceptedManager = CreatorFakeEntity.createFakeManager();    //ФэйкМенеджер без Id
        Manager managerResult = managerService.createManager(managerTemplate);
        verify(managerRepository).save(managerTemplate);    //Проверка что managerRepository.save(acceptedManager) вызывался.
//        assertEquals(managerTemplate, managerResult);      //Проверка что результат выполнения
        compareManager(managerTemplate, managerResult);     //проверка вынесена в отдельный метод
    }

    @Test
    @DisplayName("Positive test. Get manager by Id.")
    void testGetManagerById() {
        when(managerRepository.findById(2l)).thenReturn(Optional.of(managerTemplate)); // Стаббинг: определение поведения, Возвращает ФэйкМенеджер с Id
        Manager managerResult = managerService.getManagerById(2l);
        verify(managerRepository).findById(2l);             //Проверка что managerRepository.getManagerById(Long l) вызывался.
        compareManager(managerTemplate, managerResult);     //проверка вынесена в отдельный метод
    }


    @Test
    @DisplayName("Positive test. Get all managers.")
    void testGetAllManagers() {
        when(managerRepository.findAll()).thenReturn(managerList);  // Стаббинг: определение поведения
        List<Manager> result = managerService.getAllManagers();
        verify(managerRepository).findAll();    //Проверка что managerRepository.findAll() вызывался.
        assertEquals(managerList, result);      //Проверка что результат выполнения
    }

    @Test
    @DisplayName("Positive test. Delete manager by Id.")
    void testDeleteManagerById() {
        doNothing().when(managerRepository).deleteById(anyLong());  // Стаббинг с использованием doReturn-when
        managerService.deleteManagerById(2L);
        verify(managerRepository).deleteById(anyLong()); //Проверка что метод deleteById(Long 2L) вызывался.
    }

    private void compareManager(Manager managerTemplate, Manager managerResult) {
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