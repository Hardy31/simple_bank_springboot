package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CSVParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvTest {
//    @Test
//    void getAllTest() throws IOException {
//        String fileName = "src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/manager.csv";
//        Reader reader = new FileReader(fileName);
////        CsvMapper mapper = new CsvMapper();
//
////        mapper.registerModule(new JavaTimeModule());
//
//        ObjectMapper mapper =  new CsvMapper().registerModule(new JavaTimeModule());
//        CsvSchema schema = mapper.schemaFor(Manager.class)
//                .withColumnSeparator(',').withSkipFirstDataRow(true);
//        MappingIterator<Manager> iterator = mapper
//                .readerFor(Manager.class)
//                .with(schema)
//                .readValues(reader);
//
//        List<Manager> elements = iterator.readAll();
//
//
//        System.out.println(elements);
//
//    }
    //    @Test
//    void getAllTest() throws IOException {


}
