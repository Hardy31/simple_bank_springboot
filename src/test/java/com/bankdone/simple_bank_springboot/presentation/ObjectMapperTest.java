package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.util.CreaterFakeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.bankdone.simple_bank_springboot.dto.*;


/**
 * https://habr.com/ru/companies/otus/articles/687004/
 *
 */



public class ObjectMapperTest {

    ManagerDTO managerDTO1;
    ManagerDTO managerDTO2 ;
    List<ManagerDTO> managerDTOList ;

    @BeforeEach
    void seUp(){
         managerDTO1 = CreaterFakeDTO.getManagerDTO(1L);
         managerDTO2 = CreaterFakeDTO.getManagerDTO(2L);
        managerDTOList = List.of(managerDTO1, managerDTO2);

    }

    /**
     * Подготовка objectMapper к работе с LocalDataTime. - ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
     * реобразования POJO в JSON (сериализация) objectMapper.writeValue(Куда сериализовать, что сериализовать);
     *
     * @throws IOException
     */
    @Test
    void convertListDtoToJsonFile() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();

//        ObjectMapper objectMapper1 = new ObjectMapper().findAndRegisterModules();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();
//        Object managerDTOList;
        objectMapper.writeValue(new File("src/test/java/com/bankdone/simple_bank_springboot/presentation/manajerDto.json"), managerDTO1);
        objectMapper.writeValue(new File("src/test/java/com/bankdone/simple_bank_springboot/presentation/manajerDtoList.json"), managerDTOList);



    }

    /**
     * Подготовка objectMapper к работе с LocalDataTime. - ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
     * реобразования POJO в JSON (сериализация) objectMapper.writeValue(Куда сериализовать, что сериализовать);
     *
     * @throws IOException
     */
    @Test
    void test2() throws JsonProcessingException {
        String json = "[{\"id\":\"1\",\"firstName\":\"NameF\",\"lastName\":\"SurnameF\",\"status\":\"ACTIVE\",\"createdAt\":\"2024-03-09T03:13:27.242991118\",\"updatedAt\":null},{\"id\":\"2\",\"firstName\":\"NameF\",\"lastName\":\"SurnameF\",\"status\":\"ACTIVE\",\"createdAt\":\"2024-03-09T03:13:27.242991118\",\"updatedAt\":null}]";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

//        ManagerListDTO managerListDTO = objectMapper.readValues(json, ManagerListDTO.class);


        ManagerDTO[] managerDTOArray = objectMapper.readValue(json, ManagerDTO[].class);

        List<ManagerDTO> managerDTOL = Arrays.asList(managerDTOArray);
        ManagerListDTO managerListDTO = new ManagerListDTO(managerDTOL);
        System.out.println(managerListDTO.toString());
    }

}
