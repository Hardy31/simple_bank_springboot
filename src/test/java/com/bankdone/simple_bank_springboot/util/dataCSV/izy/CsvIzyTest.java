package com.bankdone.simple_bank_springboot.util.dataCSV.izy;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CSVParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvIzyTest {
//    @Test
//    void getAllTest() throws IOException {
//        String fileName = "src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/manager.csv";
//        Reader reader = new FileReader(fileName);
//        CsvMapper csvMapper = new CsvMapper();
//        CsvSchema schema =  csvMapper.schemaFor(ManagH.class).withHeader().withColumnSeparator(',');
//        MappingIterator<Manager> iterator = csvMapper
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
        @Test
    void getAllTest1() throws IOException {
            CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Man> orderLines = csvMapper.readerFor(Man.class)
                    .with(orderLineSchema)
                    .readValues(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/izy/manH.csv"));
            new ObjectMapper()
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .writeValue(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/izy/ManFromCsv.json"), orderLines.readAll());
        }

        @Test
    void getAllTest2() throws IOException {
            String stringDate = "1999-02-24 12:10:12.123456";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            LocalDateTime ldt = LocalDateTime.parse(stringDate, formatter);
            System.out.println("LocalDateTime - " + ldt);


            String stringDate1 = "1999-02-25 12:10:12.123456Z";
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSX");
            ZonedDateTime parse = ZonedDateTime.parse(stringDate1, formatter1);
            System.out.println("LocalDateTime - " + parse);


            String stringDate2 = "1999-02-26T12:10:12.123456Z";
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX");
            ZonedDateTime parse2 = ZonedDateTime.parse(stringDate2, formatter2);
            System.out.println("LocalDateTime - " + parse2);



    }

//    @Test
//    void getAllTest3() throws IOException {
//        String fileName = "src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/izy/managerH.csv";
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSX");
//        try {
//            File csvFile = new File(fileName);
//            CsvMapper csvMapper = new CsvMapper();
//            CsvSchema csvSchema = csvMapper.schemaFor(ManagH.class).withHeader();
//            MappingIterator<ManagH> mappingIterator = csvMapper.readerFor(ManagH.class).with(csvSchema).readValues(csvFile);
//            List<ManagH> managers = mappingIterator.readAll();
//
//            // Дальнейшая обработка полученных данных
//            for (ManagH manager : managers) {
//                // Действия с каждым объектом Manager
//                System.out.println(manager);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @Test
    void getAllTest4() throws IOException {


    }


@Test
    void getAllTest6() throws IOException {
        CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Man> orderLines = csvMapper.readerFor(ManagerString.class)
                .with(orderLineSchema)
                .readValues(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/izy/managerH.csv"));
        new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .writeValue(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/izy/ManagerStringFromCsv.json"), orderLines.readAll());
    }






}
