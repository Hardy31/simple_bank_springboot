package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.impl.ManagerServiceImpl;
import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
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

//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime createAt;

//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime UpdatedAt;
    ManagerCreatDTO managerCreatFackDTO;
    ManagerDTO managerFackDTO;

    @BeforeEach
    void setUp() {
        managerTemplate = CreatorFakeEntity.getFakeManager(1L);
        managerListTemplate = List.of(managerTemplate, CreatorFakeEntity.getFakeManager(2L));
        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();

        LocalDateTime createdAt = managerTemplate.getCreatedAt();
        LocalDateTime UpdatedAt = managerTemplate.getUpdatedAt();
        managerCreatFackDTO = CreaterFakeDTO.getManagerToCreate();
        managerFackDTO = CreaterFakeDTO.getManagerDTO(1l);

    }

    @Test
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    void createTest() throws Exception {
        when(managerService.createManager(any(ManagerCreatDTO.class))).thenReturn(managerFackDTO);

        Manager fakManager = CreatorFakeEntity.createFakeManager();
        String json = new ObjectMapper().writeValueAsString(fakManager);
        log.info("ManagerControllerTest->createTest() postRequestJson : json________ {}", json);



        RequestBuilder request = MockMvcRequestBuilders
                .post("/rest/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        createAt = CreaterFakeDTO.now;
        System.out.println(createAt);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(managerTemplate.getId()))
                .andExpect(jsonPath("$.firstName").value(managerTemplate.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(managerTemplate.getLastName()))
                .andExpect(jsonPath("$.status").value(managerTemplate.getStatus().toString()))

                .andExpect( jsonPath("$.createdAt").value(managerTemplate.getCreatedAt()))     //как протестировать дату
                .andExpect(jsonPath("$.updatedAt").value(managerTemplate.getUpdatedAt()))
                .andReturn();
    }



    @Test
    void getAllManagersTest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(managerListTemplate);
        System.out.println(json);


        RequestBuilder request = MockMvcRequestBuilders
                .get("/rest/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);


        when(managerService.getAllManagers()).thenReturn(managerListTemplate);

//        List<Manager> resultList = managerService.getAllManagers();


        MvcResult result = mockMvc.perform(get("/rest/managers")).andExpect(status().isOk()).andReturn();
//
//                // Выполнение запроса get и проверка результатов
//        mockMvc.perform(get("/rest/managers")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()) // ожидается статус 200 ОК но выдает 404
////                .andExpect((ResultMatcher) jsonPath("$",  hasSize(2))) // ожидается, что будет список с тем же размером
//                .andExpect((ResultMatcher) jsonPath("$[0].id").value(managerListTemplate.get(0).getId())); // здесь и далее проверяем фактические данные



        log.info("ManagerControllerTest -> getAllManagersTest() Status - {} Формат ответа - {} ", result.getResponse().getStatus(), result.getResponse().getContentType());

//            mockMvc.perform(get("/rest/managers")).andExpect(status().isOk(), content().json());
//        List<Manager> resultList = ObjectMapper.readValue(result.getResponse().getContentAsString(), List<Manager>.class);

        verify(managerService, times(1)).getAllManagers();


//        // Конфигурируем поведение: при вызове getAllManagers, мок вернет созданный список
//        Mockito.when(managerService.getAllManagers()).thenReturn(managerListTemplate);
//
//        // Выполнение запроса get и проверка результатов
//        mockMvc.perform(get("/rest/managers")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()) // ожидается статус 200 ОК но выдает 404
////                .andExpect((ResultMatcher) jsonPath("$",  hasSize(2))) // ожидается, что будет список с тем же размером
//                .andExpect((ResultMatcher) jsonPath("$[0].id").value(managerListTemplate.get(0).getId())); // здесь и далее проверяем фактические данные
//        verify(managerService, times(1)).getAllManagers();


    }


    @Test
    void getManagerById() throws Exception {

        when(managerService.getManagerById(any())).thenReturn(managerTemplate);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/rest/managers/{id}", 6L)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

//                  import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//                  для корректной работы  jsonPath("$.id").
                .andExpect(jsonPath("$.id").value(managerTemplate.getId()))
                .andExpect(jsonPath("$.firstName").value(managerTemplate.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(managerTemplate.getLastName()))
                .andExpect(jsonPath("$.status").value(managerTemplate.getStatus().toString()))
//                .andExpect( jsonPath("$.createdAt").value(managerTemplate.getCreatedAt().toString()))     //как протестировать дату
                .andExpect(jsonPath("$.updatedAt").value(managerTemplate.getUpdatedAt()));


        verify(managerService, times(1)).getManagerById(anyLong());
    }

}