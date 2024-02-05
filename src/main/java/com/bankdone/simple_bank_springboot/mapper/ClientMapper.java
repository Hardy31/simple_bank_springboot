package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.ClientCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ClientDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",  imports = LocalDateTime.class)
public interface ClientMapper {
    ClientDTO convertToDTO(Client client);
    Client convertToEntity(ClientDTO clientDTO);

    List<ClientDTO> convertToClientDTOList(List<Client> clientList);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Client createToEntity(ClientCreatDTO clientCreatDTO);
}
