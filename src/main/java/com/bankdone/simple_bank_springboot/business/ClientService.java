package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ClientService {
    List<Client> getAllClients();

    Client getClientById(Long id);

    Client create(Client client);

    Client editClieny(Client client);

    void delite(@PathVariable long id);

    List<Client> getAllClientsByManager_id(Long id);

    Client getClientByPhone(String phone);

    List<Client> getClientsByAddress(String address);

    List<Client> getAllClientsByStatus(ClientStatus status);

    List<Client> getAllClientsCreatedBetween(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo);


}
