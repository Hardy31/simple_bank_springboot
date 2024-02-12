package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.AgreementCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AgreementDTO;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",  imports = LocalDateTime.class)
public interface AgreementMapper {
    @Mapping(target = "accountDTO", source = "agreement.account")
    @Mapping(target = "productDTO", source = "agreement.product")
    AgreementDTO convertToDTO(Agreement agreement);
    Agreement convertToEntity(AgreementDTO agreementDTO);

    List<AgreementDTO> convertToAgreementDTOList (List<Agreement> agreementList);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Agreement createToEntity(AgreementCreateDTO agreementCreateDTO);

}
