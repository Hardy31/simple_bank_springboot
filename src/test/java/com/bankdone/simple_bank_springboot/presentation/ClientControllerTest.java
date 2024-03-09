package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.business.impl.ClientServiceImpl;
import com.bankdone.simple_bank_springboot.dto.*;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ClientController.class)
@Slf4j
public class ClientControllerTest {
    @MockBean
    ClientServiceImpl clientService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private ClientController clientController;
    ClientCreatDTO clientCreatDTOFake;
    ClientDTO clientDTOFake, clientDTOFake1;
    ClientListDTO clientListDTO;

    @BeforeEach
    void seUp(){
        clientDTOFake = CreaterFakeDTO.getClientDTO();
        clientDTOFake1 = CreaterFakeDTO.getClientDTO();
        clientListDTO = new ClientListDTO(List.of(clientDTOFake, clientDTOFake1));

//        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        ObjectMapper objectMapper = new ObjectMapper();

    }

    @Test
    void createClientTest() throws Exception{
        clientCreatDTOFake = CreaterFakeDTO.getClientToCreate();

//        ObjectMapper objectMapper = new ObjectMapper();

        //Данной строкой подключаем модуль в ObjectMapper для того чтобы корректно парсил LacalDataTime
        objectMapper.findAndRegisterModules();

        //
        String requestJson = objectMapper.writeValueAsString(clientDTOFake);
        log.info("Requesr Client {}", requestJson);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        //создаем правило по которому clientService.create(ClientCreatDTO) возвращает ClientDTO
        when(clientService.create(any(ClientCreatDTO.class))).thenReturn(clientDTOFake);

        //mockMvc.perform(request) - ockMvc.выполнить (запрос) и ожидать ответ состатусом ОК и вернуть
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        //Из полученного результата взять ответ и преобразовать в JSON
        String responsJson = mvcResult.getResponse().getContentAsString();
        log.info("Respons Client {}", responsJson);

        //перобразуем полученный JSON  c бект класса ClientDTO
        ClientDTO actualClientDTO = objectMapper.readValue(responsJson, ClientDTO.class);

        //стравниваем  clientDTOFake (который создается на основе  clientDTOFake  с добавлением в него Id b createAt)
        // b
        compareDTO(clientDTOFake, actualClientDTO);
        verify(clientService, times(1)).create(any(ClientCreatDTO.class));

    }

    @Test
    void getById() throws Exception {
        Long id = 3L;
        RequestBuilder request = MockMvcRequestBuilders.get("/rest/clients/{id}", id).contentType(MediaType.APPLICATION_JSON);
        when(clientService.getClientById(anyLong())).thenReturn(clientDTOFake);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        objectMapper.findAndRegisterModules();
        ClientDTO actualClientDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ClientDTO.class);
        compareDTO(clientDTOFake,actualClientDTO);
        verify(clientService, times(1)).getClientById(Long.valueOf(clientDTOFake.getId()));
    }
    @Test
    void geAllClients() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/rest/clients").contentType(MediaType.APPLICATION_JSON);
        when(clientService.getAllClients()).thenReturn(clientListDTO.getClientDTOList());
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        //Из полученного результата взять ответ и преобразовать в JSON
        String responsJson = mvcResult.getResponse().getContentAsString();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


        ClientDTO[] actualClientDTOArray = objectMapper.readValue(responsJson, ClientDTO[].class);
        List<ClientDTO> listClientDTO = Arrays.asList(actualClientDTOArray);
        ClientListDTO actualClientDTOList = new ClientListDTO(listClientDTO);



        compareListDTO(clientListDTO, actualClientDTOList);
        verify(clientService, times(1)).getAllClients();

    }


    @Test
    void editClient() throws Exception {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ClientDTO clientDtoForApdate = CreaterFakeDTO.getClientDTOFoUpdate();
        //
        String requestJson = objectMapper.writeValueAsString(clientDtoForApdate);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDtoForApdate));

        when(clientService.editClieny(any(ClientDTO.class))).thenReturn(clientDtoForApdate);

        var mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        ClientDTO actualClientDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ClientDTO.class);
        compareDTO(clientDtoForApdate, actualClientDTO);
        verify(clientService, times(1)).editClieny(clientDtoForApdate);





    }

    @Test
    void deleteClientById() throws Exception {
        Long id = 3L;
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/rest/clients/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk());
        verify(clientService, times(1)).delite(anyLong());

    }

    private void compareDTO(ClientDTO expectedDTO, ClientDTO actualDTO) {
        assertAll(
                () -> assertEquals(expectedDTO.getId(), actualDTO.getId()),
                () -> assertEquals(expectedDTO.getStatus(), actualDTO.getStatus()),
                () -> assertEquals(expectedDTO.getTaxCode(), actualDTO.getTaxCode()),
                () -> assertEquals(expectedDTO.getFirstName(), actualDTO.getFirstName()),
                () -> assertEquals(expectedDTO.getLastName(), actualDTO.getLastName()),
                () -> assertEquals(expectedDTO.getEmail(), actualDTO.getEmail()),
                () -> assertEquals(expectedDTO.getAddress(), actualDTO.getAddress()),
                () -> assertEquals(expectedDTO.getPhone(), actualDTO.getPhone()),
                () -> assertEquals(expectedDTO.getManagerDTO().getId(), actualDTO.getManagerDTO().getId())
        );
    }

    private void compareListDTO(ClientListDTO expectedListDTO, ClientListDTO actualListDTO) {
        assertEquals(expectedListDTO.getClientDTOList().size(), actualListDTO.getClientDTOList().size());
        for (int i = 0; i < expectedListDTO.getClientDTOList().size(); i++) {
            compareDTO(expectedListDTO.getClientDTOList().get(i), actualListDTO.getClientDTOList().get(i));
        }
    }
}
