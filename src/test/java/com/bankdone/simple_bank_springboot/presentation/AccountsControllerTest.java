package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.business.impl.AccountServiceImpl;
import com.bankdone.simple_bank_springboot.dto.*;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountsController.class)
@Slf4j
class AccountsControllerTest {
    @MockBean
    private AccountServiceImpl accountService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    ClientDTO clientDTO;
    AccountDTO accountDTO;
    AccountListDTO accountListDTO;


    @BeforeEach
    void setUp(){
        clientDTO = CreaterFakeDTO.getClientDTO();
        accountDTO = CreaterFakeDTO.getAccountDTO();
        List<AccountDTO> accountDTOList = List.of(accountDTO,accountDTO);
        accountListDTO = new AccountListDTO(accountDTOList);
    }

    @Test
    void getAll() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/rest/accounts")
                .contentType(MediaType.APPLICATION_JSON);
        when(accountService.getAll()).thenReturn(accountListDTO);

        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        String responsJson = mvcResult.getResponse().getContentAsString();

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        AccountListDTO actualAccountListDTO = objectMapper.readValue(responsJson, AccountListDTO.class);

//        List<AccountDTO> actualAccountDTOList = Arrays.asList(actualAccountDTOArray);

        compareListDTO(accountListDTO, actualAccountListDTO);
        verify(accountService,times(1)).getAll();

    }

    @Test
    void getById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
            .get("/rest/accounts/{id}", 6L)
            .contentType(MediaType.APPLICATION_JSON);
        when(accountService.getById(anyLong())).thenReturn(accountDTO);

        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        String responsJson = mvcResult.getResponse().getContentAsString();

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        AccountDTO actualAccountDTO = objectMapper.readValue(responsJson, AccountDTO.class);
        compareDTO(accountDTO, actualAccountDTO);

    }

    @Test
    void create() throws Exception {
        AccountCreateDTO accountToCreate = CreaterFakeDTO.getAccountToCreate();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String requestJson = objectMapper.writeValueAsString(accountToCreate);
        RequestBuilder request =MockMvcRequestBuilders
            .post("/rest/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson);
        when(accountService.create(any(AccountCreateDTO.class))).thenReturn(accountDTO);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        String responsJson = mvcResult.getResponse().getContentAsString();
        AccountDTO actualAccountDTO = objectMapper.readValue(responsJson, AccountDTO.class);
        compareDTO(accountDTO, actualAccountDTO);
        verify(accountService, times(1)).create(any(AccountCreateDTO.class));
    }

    @Test
    void editAccount() throws Exception {

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        AccountDTO accountDtoForApdate = CreaterFakeDTO.getAccountDTOForUpdate();
        //
        String requestJson = objectMapper.writeValueAsString(accountDtoForApdate);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/rest/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDtoForApdate));

        when(accountService.edit(any(AccountDTO.class))).thenReturn(accountDtoForApdate);

        var mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        AccountDTO actualAccountDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AccountDTO.class);
        compareDTO(accountDtoForApdate, actualAccountDTO);
        verify(accountService, times(1)).edit(any(AccountDTO.class));

    }

    @Test
    void delete() throws Exception {

        Long id = 3L;
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/rest/accounts/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk());
        verify(accountService, times(1)).delite(anyLong());
    }

    private void compareDTO(AccountDTO expectedDTO, AccountDTO actualDTO) {
        assertAll(
                () -> assertEquals(expectedDTO.getId(), actualDTO.getId()),
                () -> assertEquals(expectedDTO.getName(), actualDTO.getName()),
                () -> assertEquals(expectedDTO.getType(), actualDTO.getType()),
                () -> assertEquals(expectedDTO.getStatus(), actualDTO.getStatus()),
                () -> assertEquals(expectedDTO.getBalance(), actualDTO.getBalance()),
                () -> assertEquals(expectedDTO.getCurrencyCode(), actualDTO.getCurrencyCode()),
                () -> assertEquals(expectedDTO.getCreatedAt(), actualDTO.getCreatedAt()),
                () -> assertEquals(expectedDTO.getUpdatedAt(), actualDTO.getUpdatedAt()),
                () -> assertEquals(expectedDTO.getClientDTO(), actualDTO.getClientDTO())

        );
    }
    private void compareListDTO(AccountListDTO expectedListDTO, AccountListDTO actualListDTO) {
        assertEquals(expectedListDTO.getAccountDTOList().size(), actualListDTO.getAccountDTOList().size());
        for (int i = 0; i < expectedListDTO.getAccountDTOList().size(); i++) {
            compareDTO(expectedListDTO.getAccountDTOList().get(i), actualListDTO.getAccountDTOList().get(i));
        }
    }
}