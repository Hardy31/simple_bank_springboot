package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.dto.ClientCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ClientDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import com.bankdone.simple_bank_springboot.mapper.ClientMapper;
import com.bankdone.simple_bank_springboot.mapper.ManagerMapper;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ManagerRepository managerRepository;
    private Manager managerTemplate;
    private  ManagerDTO managerDTO;
    private Client clientTemplate;
    private  ClientDTO  clientDTOFake;
    private ClientService clientService ;
    private List<Client> clientList;
    private ClientMapper clientMapper;
    private ManagerMapper managerMapper;

    @BeforeEach
    void setUp(){
        managerTemplate = CreatorFakeEntity.getFakeManager(2l);
        clientTemplate = CreatorFakeEntity.getFakeClient(3l);
        log.info("TEST setUp clientTemplate {}", clientTemplate);
        managerDTO = CreaterFakeDTO.getManagerDTO(2L);
        clientDTOFake = CreaterFakeDTO.getClientDTO();
        clientService = new ClientServiceImpl(clientRepository,managerRepository, clientMapper);
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
        Client clientFake = CreatorFakeEntity.creatFakeClient(2L);
        ClientCreatDTO clientCreatDTO = CreaterFakeDTO.getClientToCreate();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientTemplate));
        Long id = clientTemplate.getId();
        ClientDTO clientDTO = clientService.getClientById(id);
        log.info("TEST  {}", clientDTO);
        log.info("TEST  {}", clientDTOFake);
        verify(clientRepository).findById(anyLong());
        compareClientDTO(clientDTO, clientDTOFake);
    }

    @Test
    void testGetAllClients() {
//        when(clientRepository.findAll()).thenReturn(clientList);
//        List<ClientDTO> resuly = clientService.getAllClients();
//        verify(clientRepository).findAll();
//        assertEquals(clientList,resuly );
    }


    private void compareClientDTO(ClientDTO clientTemplate, ClientDTO clientReslt) {
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
                () -> assertEquals(clientTemplate.getManagerDTO().getId(), clientReslt.getManagerDTO().getId())
        );
    }


    @Test
    void create() {
//        when(clientRepository.save(any())).thenReturn(clientTemplate);
//        Client assertedClient = CreatorFakeEntity.creatFakeClient(3l);
//        Client resultClient = clientService.create(assertedClient);
//        verify(clientRepository).save(assertedClient);
//        compareClient(clientTemplate, resultClient);
    }

    @Test
    void editClieny() {
//        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientTemplate));
//        Client resultClient = clientService.editClieny(clientTemplate);
//        verify(clientRepository).save(any());
//        compareClient(clientTemplate, resultClient);
    }

    @Test
    void delite() {
//        doNothing().when(clientRepository).deleteById(anyLong());
//        clientService.delite(3l);
//        verify(clientRepository).deleteById(anyLong());


    }

    @Test
    void getClientByPhone() {
//        when(clientRepository.findClientByPhone(any())).thenReturn(Optional.of(clientTemplate));
//        Client resultClient = clientService.getClientByPhone(clientTemplate.getPhone());
//        verify(clientRepository).findClientByPhone(clientTemplate.getPhone());
//        compareClient(resultClient,clientTemplate);
////        verify(cl)
//

    }

    @Test
    void getAllClientsByManager_id() {
//        when(clientRepository.findAllByManager_Id(anyLong())).thenReturn(clientList);
//        List<Client> resultList = clientService.getAllClientsByManager_id(managerTemplate.getId());
//        verify(clientRepository).findAllByManager_Id(managerTemplate.getId());
//        assertEquals(resultList,clientList);
    }

    @Test
    void getAllClientsByStatus() {
//        when(clientRepository.findClientByStatus(any(ClientStatus.class))).thenReturn(clientList);
//        List<Client> resultList = clientService.getAllClientsByStatus(clientTemplate.getStatus());
//        verify(clientRepository).findClientByStatus(any(ClientStatus.class));
//        assertArrayEquals(resultList.toArray(), clientList.toArray());

    }

//    @Test
//    void getAllClientsCreatedBetween() {
//        when(clientRepository.findClientByCreatedAtIsBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(clientList);
//        List<Client> resultList = clientService.getAllClientsCreatedBetween(clientTemplate.getCreatedAt().minusMonths(1l),clientTemplate.getCreatedAt().plusMonths(1l));
//        verify(clientRepository).findClientByCreatedAtIsBetween(any(LocalDateTime.class), any(LocalDateTime.class));
//        assertArrayEquals(resultList.toArray(), clientList.toArray());
//    }
}