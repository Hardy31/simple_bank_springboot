package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import com.bankdone.simple_bank_springboot.exeption.DefaultManagerNotFoundExeption;
import com.bankdone.simple_bank_springboot.exeption.ErrorMessageTest;
import com.bankdone.simple_bank_springboot.util.dataCSV.ManagerStr;
import com.bankdone.simple_bank_springboot.util.dataCSV.izy.Man;
import com.bankdone.simple_bank_springboot.util.dataCSV.izy.ManagerString;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class CSVParser {


@Test
    public void getManagerStringList() throws IOException {
        CsvSchema managerSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<ManagerStr> ManagerStrIterator = csvMapper.readerFor(ManagerStr.class)
                .with(managerSchema)
                .readValues(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/manager.csv"));
        List<ManagerStr> managerStrList = ManagerStrIterator.readAll();

        managerStrList.stream().forEach(System.out::println);

    System.out.println(managerStrList.get(5).getCreatedAt());

    int Number = 1;
    String idString = managerStrList.get(Number).getId().toString();

    Long id = Long.parseLong(idString);
    String ldtCreateAt = managerStrList.get(Number).getCreatedAt();
    LocalDateTime ldtUpdateAt = null;
    if (!managerStrList.get(Number).getCreatedAt().isEmpty()){
        String stringUpdateAt = managerStrList.get(Number).getUpdatedAt();
        ldtUpdateAt = convertsStringToLocalDateTime(stringUpdateAt);
    }
    String status = managerStrList.get(Number).getStatus();
    ManagerDTO result = new ManagerDTO(
            idString,
            managerStrList.get(Number).getFirstName(),
            managerStrList.get(Number).getLastName(),
            status,
            convertsStringToLocalDateTime(ldtCreateAt),
            ldtUpdateAt
        );
    System.out.println(" MANAGER DTO - " + result);

//        new ObjectMapper()
//                .configure(SerializationFeature.INDENT_OUTPUT, true)
//                .writeValue(new File("src/test/java/com/bankdone/simple_bank_springboot/util/dataCSV/izy/ManagerStringFromCsv.json"), ManagerStrIterator.readAll());
//        return managerStrList;


    }

    public LocalDateTime convertsStringToLocalDateTime (String ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        return LocalDateTime.parse(ldt, formatter);
    }

}
