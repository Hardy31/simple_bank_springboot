package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.impl.ManagerServiceImpl;
import com.bankdone.simple_bank_springboot.dto.*;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.io.OutputStream;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ManagerController.class)
@Slf4j
class ManagerControllerTest {
    @MockBean
    private ManagerServiceImpl managerService;
//    @InjectMocks
    private ManagerController managerController;
//   @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    Manager managerTemplate;
    List<Manager> managerListTemplate;
    LocalDateTime createAt;
    LocalDateTime UpdatedAt;
    ManagerCreatDTO managerCreatFakDTO;
    ManagerDTO managerDTO, managerDTO1;
    private List<ManagerDTO> managerDTOList;
    private ManagerListDTO managerListDTO;

    @BeforeEach
    void setUp() {
        managerTemplate = CreatorFakeEntity.getFakeManager(1L);
        managerListTemplate = List.of(managerTemplate, CreatorFakeEntity.getFakeManager(2L));
//        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();

        LocalDateTime createdAt = managerTemplate.getCreatedAt();
        LocalDateTime UpdatedAt = managerTemplate.getUpdatedAt();
        managerCreatFakDTO = CreaterFakeDTO.getManagerToCreate();
        managerDTO = CreaterFakeDTO.getManagerDTO(1l);
        managerDTO1 = CreaterFakeDTO.getManagerDTO(2l);

        managerDTOList = new ArrayList<>(List.of(managerDTO,managerDTO1));
        managerListDTO = new ManagerListDTO(managerDTOList);
    }

    @Test
    void createTest() throws Exception {
        when(managerService.createManager(any(ManagerCreatDTO.class))).thenReturn(managerDTO);

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;
        Manager fakManager = CreatorFakeEntity.createFakeManager();
        String json = new ObjectMapper().writeValueAsString(fakManager);
//        String json = new ObjectMapper().registerModule(new JavaTimeModule()).toString();;

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

        RequestBuilder request =  MockMvcRequestBuilders
                .get("/rest/managers")
                .contentType(MediaType.APPLICATION_JSON);

        when(managerService.getAllManagers()).thenReturn(managerDTOList);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        //Из полученного результата взять ответ и преобразовать в JSON
        String responsJson = mvcResult.getResponse().getContentAsString();
        log.info("Respons Client {} responsJson_____________", responsJson);

        //перобразуем полученный JSON  c бект класса ClientDTO

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


        /**
         * Не нравится вот это рещение!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         */
        ManagerDTO[] actualManagerDTOArray = objectMapper.readValue(responsJson, ManagerDTO[].class);
         List<ManagerDTO> liatManagerDTO = Arrays.asList(actualManagerDTOArray);
        ManagerListDTO actualManagerDTOList = new ManagerListDTO(liatManagerDTO);



        compareListDTO(new ManagerListDTO(managerDTOList), actualManagerDTOList);
        verify(managerService, times(1)).getAllManagers();
    }


    @Test
    void getManagerByIdTest() throws Exception {

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
    private void compareDTO(ManagerDTO expectedDTO, ManagerDTO actualDTO) {
        assertAll(
                () -> assertEquals(expectedDTO.getId(), actualDTO.getId()),
                () -> assertEquals(expectedDTO.getStatus(), actualDTO.getStatus()),
                () -> assertEquals(expectedDTO.getFirstName(), actualDTO.getFirstName()),
                () -> assertEquals(expectedDTO.getLastName(), actualDTO.getLastName()),
                () -> assertEquals(expectedDTO.getCreatedAt(), actualDTO.getCreatedAt()),
                () -> assertEquals(expectedDTO.getUpdatedAt(), actualDTO.getUpdatedAt())

        );
    }
    private void compareListDTO(ManagerListDTO expectedListDTO, ManagerListDTO actualListDTO) {
        assertEquals(expectedListDTO.getManagerDTOList().size(), actualListDTO.getManagerDTOList().size());
        for (int i = 0; i < expectedListDTO.getManagerDTOList().size(); i++) {
            compareDTO(expectedListDTO.getManagerDTOList().get(i), actualListDTO.getManagerDTOList().get(i));
        }
    }


}