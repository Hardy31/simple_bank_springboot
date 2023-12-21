package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    void setUp(){
        managerTemplate = CreatorFakeEntity.getFakeManager(2l);
        clientTemplate = CreatorFakeEntity.getFakeClient(3l);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    void attempt(){
        clientTemplate = CreatorFakeEntity.getFakeClient(3l);
        System.out.println(clientTemplate);
        verify(clientRepository).findById(3l);
    }

    @Test
    @DisplayName("Positive test. GetClientById.")
    void testGetClientById() {
        when(clientRepository.findById(anyLong()).get()).thenReturn(clientTemplate);

        Client clientResult = clientService.getClientById(3l);
        verify(clientRepository).findById(3l);
        compareClient(clientTemplate, clientResult);
    }

    @Test
    void testGetAllClients() {

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


}