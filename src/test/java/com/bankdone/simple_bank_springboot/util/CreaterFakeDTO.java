package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class CreaterFakeDTO {

    public static LocalDateTime now = LocalDateTime.now();

    public static ManagerDTO getManagerDTO(Long id) {
        ManagerDTO managerFackDTO = new ManagerDTO(
                id.toString(),
                "NameF",
                "SurnameF",
                "ACTIVE",
                now,
//                null,
                null
//                Timestamp.valueOf("2023-04-02 00:00:00").toLocalDateTime()
//                LocalDateTime.of("2023-04-02 00:00:00")
        );

        log.info("CreaterFakeDTO ggetManagerDTO(Long id) : {}", managerFackDTO);
        return managerFackDTO;
    }

    public static ManagerCreatDTO getManagerToCreate() {
        ManagerCreatDTO managerCreatFackDTO = new ManagerCreatDTO(
                null,
                "NameF",
                "SurnameF",
                "ACTIVE",
//                Timestamp.valueOf(now).toLocalDateTime(),
                null,
                null
        );
        log.info("CreaterFakeDTO getManagerToCreate() : {}", managerCreatFackDTO.toString());
        return managerCreatFackDTO;


//        public static CreateTransactionDTO CreateFakeTransactionDTO(){
////        Account debetAccountTemplate = CreatorFakeEntity.getFakeAccount(9L);
////        Account credetAccountTemplate = CreatorFakeEntity.getFakeAccount(8L);
//        CreateTransactionDTO createTransactionDTO =  CreateTransactionDTO.builder()
//                .type(TransactionType.DEPOSIT)
//                .amount(10000.00d)
//                .description("description")
//                .debitAccount(9L)
//                .creditAccount(8L)
//                .createdAt(LocalDateTime.now())
//                .build();
//        return createTransactionDTO;
//    }

    }
}
