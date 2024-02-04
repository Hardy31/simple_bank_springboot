package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.impl.ManagerServiceImpl;
import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerListDTO;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.io.OutputStream;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;


//https://www.youtube.com/watch?v=QTrNqhzniws

@ExtendWith(MockitoExtension.class)
@Slf4j
class ManagerControllerTest {
    @Mock
    private ManagerServiceImpl managerService;
    @InjectMocks
    private ManagerController managerController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    Manager managerTemplate;
    List<Manager> managerListTemplate;
    LocalDateTime createAt;
    LocalDateTime UpdatedAt;
    ManagerCreatDTO managerCreatFackDTO;
    ManagerDTO managerDTO;
    private List<ManagerDTO> managerDTOList;
    private ManagerListDTO managerListDTO;

    @BeforeEach
    void setUp() {
        managerTemplate = CreatorFakeEntity.getFakeManager(1L);
        managerListTemplate = List.of(managerTemplate, CreatorFakeEntity.getFakeManager(2L));
        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();

        LocalDateTime createdAt = managerTemplate.getCreatedAt();
        LocalDateTime UpdatedAt = managerTemplate.getUpdatedAt();
        managerCreatFackDTO = CreaterFakeDTO.getManagerToCreate();
        managerDTO = CreaterFakeDTO.getManagerDTO(1l);

        managerDTOList = new ArrayList<>(List.of(managerDTO));
        managerListDTO = new ManagerListDTO(managerDTOList);
    }

    @Test
    void createTest() throws Exception {
        when(managerService.createManager(any(ManagerCreatDTO.class))).thenReturn(managerDTO);

        Manager fakManager = CreatorFakeEntity.createFakeManager();
        String json = new ObjectMapper().writeValueAsString(fakManager);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/rest/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        createAt = CreaterFakeDTO.now;

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(managerTemplate.getId()))
                .andExpect(jsonPath("$.firstName").value(managerTemplate.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(managerTemplate.getLastName()))
                .andExpect(jsonPath("$.status").value(managerTemplate.getStatus().toString()))
                .andExpect(jsonPath("$.createdAt").value(managerTemplate.getCreatedAt().toString()))     //как протестировать дату
                .andExpect(jsonPath("$.updatedAt").value(managerTemplate.getUpdatedAt()))
                .andReturn();
    }

    @Test
    void getAllManagersTest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(managerListTemplate);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/rest/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        when(managerService.getAllManagers()).thenReturn(managerDTOList);

        MvcResult result = mockMvc.perform(get("/rest/managers")).andExpect(status().isOk()).andReturn();

        verify(managerService, times(1)).getAllManagers();
    }


    @Test
    void getManagerById() throws Exception {

        when(managerService.getManagerById(any())).thenReturn(managerDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/rest/managers/{id}", 6L)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(managerDTO.getId()))
                .andExpect(jsonPath("$.firstName").value(managerDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(managerDTO.getLastName()))
                .andExpect(jsonPath("$.status").value(managerDTO.getStatus().toString()))
                .andExpect( jsonPath("$.createdAt").value(managerTemplate.getCreatedAt().toString()))     //как протестировать дату
                .andExpect(jsonPath("$.updatedAt").value(managerDTO.getUpdatedAt()));

        verify(managerService, times(1)).getManagerById(anyLong());
    }

}