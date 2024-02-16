package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.AccountCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AccountDTO;
import com.bankdone.simple_bank_springboot.dto.AgreementDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",  imports = LocalDateTime.class)
public interface AccountMapper {

    @Mapping(target = "clientDTO", source = "account.client")
    @Mapping(target = "clientDTO.managerDTO", source = "account.client.manager")
    AccountDTO convertToDTO(Account account);
    Account convertToEntity(AccountDTO accountDTO);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "code", source = "currencyCode")
    Account creatToEntity(AccountCreateDTO accountCreateDTO);


    List<AccountDTO> convertToAccountDTOList (List<Account> accountList);

}
