package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.dto.ClientCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ClientDTO;
import com.bankdone.simple_bank_springboot.dto.ClientListDTO;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO create(ClientCreatDTO clientCreatDTO);

    ClientDTO editClieny(ClientDTO clientDTO);

    void delite(@PathVariable long id);

    List<ClientDTO> getAllClientsByManager_id(Long id);

    ClientDTO getClientByPhone(String phone);

    List<ClientDTO> getClientsByAddress(String address);

    List<ClientDTO> getAllClientsByStatus(ClientStatus status);

    List<ClientDTO> getAllClientsCreatedBetween(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo);


}
