package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.AccountCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AccountDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",  imports = LocalDateTime.class)
public interface AccountMapper {

    @Mapping(target = "clientDTO", source = "account.client")
    AccountDTO convertToDTO(Account account);
    Account convertToEntity(AccountDTO accountDTO);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Account creatToEntity(AccountCreateDTO accountCreateDTO);

}
