package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.entity.Client;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CsvParser {
    @Test
    public void getClientList() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
//        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JSR310Module());
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


//        objectMapper.writeValueAsString(Client.class);

        CsvSchema clientSchema = CsvSchema.emptySchema().withHeader();
        clientSchema.withColumnSeparator(' ');


//        CsvMapper csvMapper = new CsvMapper();
        CsvMapper csvMapper = (CsvMapper) new CsvMapper().findAndRegisterModules();

//        CsvMapper csvMapper = new CsvMapper();
//         csvMapper = (CsvMapper) new CsvMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);;
        MappingIterator<Client> ClientIterator = csvMapper.readerFor(Client.class)
                .with(clientSchema)
                .readValues(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCsv/Client.csv"));
//        System.out.println(ClientIterator.next().getId());



        List<Client> clientList = ClientIterator.readAll();
        clientList.stream().forEach(System.out::println);


    }
}
