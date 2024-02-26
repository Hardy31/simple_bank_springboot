package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.dto.ClientCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ClientDTO;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ClientControllerTest {

    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientController clientController;
    ClientCreatDTO clientCreatFackeDTO;
    ClientDTO clientFackeDTO;

    @BeforeEach
    void setUp(){
        clientCreatFackeDTO = CreaterFakeDTO.getClientToCreate();
        clientFackeDTO = CreaterFakeDTO.getClientDTO();


    }

    @Test
    void create() {

        when(clientService.create(any())).thenReturn(clientFackeDTO);


    }

    @Test
    void geAllClients() {
    }

    @Test
    void getById() {
    }

    @Test
    void getClientByManagerId() {
    }

    @Test
    void geClientByPhone() {
    }

    @Test
    void geClientByAddress() {
    }

    @Test
    void getAllClientByStatus() {
    }

    @Test
    void getAllClientsCreatedWithTo() {
    }

    @Test
    void editClient() {
    }

    @Test
    void deleteClientById() {
    }
}