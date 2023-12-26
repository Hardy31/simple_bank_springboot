package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for ClientServiceImplTest")
class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ManagerRepository managerRepository;
    private Manager managerTemplate;
    private Client clientTemplate;
    private ClientService clientService ;
    private List<Client> clientList;

    @BeforeEach
    void setUp(){
        managerTemplate = CreatorFakeEntity.getFakeManager(2l);
        clientTemplate = CreatorFakeEntity.getFakeClient(3l);
        clientService = new ClientServiceImpl(clientRepository);
        clientList = new ArrayList<>(List.of(clientTemplate));
    }


//    @Test
//    void attempt(){
////        clientTemplate = CreatorFakeEntity.getFakeClient(3l);
//        System.out.println(clientTemplate);
//        verify(clientRepository).findById(3l);
//    }

    @Test
    @DisplayName("Positive test. GetClientById.")
    void testGetClientById() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientTemplate));

        Client clientResult = clientService.getClientById(3l);
        verify(clientRepository).findById(3l);
        compareClient(clientTemplate, clientResult);
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(clientList);
        List<Client> resuly = clientService.getAllClients();
        verify(clientRepository).findAll();
        assertEquals(clientList,resuly );
    }


    private void compareClient(Client clientTemplate, Client clientReslt) {
        assertAll(
                () -> assertEquals(clientTemplate.getId(), clientReslt.getId()),
                () -> assertEquals(clientTemplate.getStatus(), clientReslt.getStatus()),
                () -> assertEquals(clientTemplate.getTaxCode(), clientReslt.getTaxCode()),
                () -> assertEquals(clientTemplate.getFirstName(), clientReslt.getFirstName()),
                () -> assertEquals(clientTemplate.getLastName(), clientReslt.getLastName()),
                () -> assertEquals(clientTemplate.getEmail(), clientReslt.getEmail()),
                () -> assertEquals(clientTemplate.getAddress(), clientReslt.getAddress()),
                () -> assertEquals(clientTemplate.getPhone(), clientReslt.getPhone()),
                () -> assertEquals(clientTemplate.getCreatedAt(), clientReslt.getCreatedAt()),
                () -> assertEquals(clientTemplate.getUpdatedAt(), clientReslt.getUpdatedAt()),
                () -> assertEquals(clientTemplate.getManager().getId(), clientReslt.getManager().getId())
        );
    }


    @Test
    void create() {
        when(clientRepository.save(any())).thenReturn(clientTemplate);
        Client assertedClient = CreatorFakeEntity.creatFakeClient(3l);
        Client resultClient = clientService.create(assertedClient);
        verify(clientRepository).save(assertedClient);
        compareClient(clientTemplate, resultClient);
    }

    @Test
    void editClieny() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientTemplate));
        Client resultClient = clientService.editClieny(clientTemplate);
        verify(clientRepository).save(any());
        compareClient(clientTemplate, resultClient);
    }

    @Test
    void delite() {
        doNothing().when(clientRepository).deleteById(anyLong());
        clientService.delite(3l);
        verify(clientRepository).deleteById(anyLong());


    }

    @Test
    void getClientByPhone() {
        when(clientRepository.findClientByPhone(any())).thenReturn(Optional.of(clientTemplate));
        Client resultClient = clientService.getClientByPhone(clientTemplate.getPhone());
        verify(clientRepository).findClientByPhone(clientTemplate.getPhone());
        compareClient(resultClient,clientTemplate);
//        verify(cl)


    }

    @Test
    void getAllClientsByManager_id() {
        when(clientRepository.findAllByManager_Id(anyLong())).thenReturn(clientList);
        List<Client> resultList = clientService.getAllClientsByManager_id(managerTemplate.getId());
        verify(clientRepository).findAllByManager_Id(managerTemplate.getId());
        assertEquals(resultList,clientList);
    }

    @Test
    void getAllClientsByStatus() {
        when(clientRepository.findClientByStatus(any(ClientStatus.class))).thenReturn(clientList);
        List<Client> resultList = clientService.getAllClientsByStatus(clientTemplate.getStatus());
        verify(clientRepository).findClientByStatus(any(ClientStatus.class));
        assertArrayEquals(resultList.toArray(), clientList.toArray());

    }

    @Test
    void getAllClientsCreatedBetween() {
        when(clientRepository.findClientByCreatedAtIsBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(clientList);
        List<Client> resultList = clientService.getAllClientsCreatedBetween(clientTemplate.getCreatedAt().minusMonths(1l),clientTemplate.getCreatedAt().plusMonths(1l));
        verify(clientRepository).findClientByCreatedAtIsBetween(any(LocalDateTime.class), any(LocalDateTime.class));
        assertArrayEquals(resultList.toArray(), clientList.toArray());
    }
}