package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.impl.ManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//https://www.youtube.com/watch?v=QTrNqhzniws

@ExtendWith(MockitoExtension.class)
class ManagerControllerTest  {
    @Mock
    private ManagerServiceImpl managerService;

    @InjectMocks
    private ManagerController managerController;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }

    @Test
    void getManagerByIdTest() throws Exception{

            mockMvc.perform(get("/rest/managers")).andExpect(status().isOk());

    }
}