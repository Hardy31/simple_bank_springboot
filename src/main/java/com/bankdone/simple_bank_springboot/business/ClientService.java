package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс  ClientService
 * @Service
 * @Transactional
 *
 * List<Client> findAllByManager_Id(Long id); использован построитель запросов по имени метода.
 * Поиск клиента по Id
 *
 * Optional<Client> findClientByPhone(String phone); использован построитель запросов по имени метода.
 * Поиск клиента по телефону
 *
 *   List<Client> findClientByAddress(String address); использован построитель запросов по имени метода.
 * Поиск клиентов по адресу
 *
 * List<Client> findClientByStatus(ClientStatus status); использован построитель запросов по имени метода.
 * Поиск клиентов по статусуадресу
 *
 * List<Client> findClientByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );
 * использован построитель запросов по имени метода.
 * Возвращает список клиентов которые были созданы в промежутке переданных дат ( с по).
 *
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-11-19
 */


@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Cacheable("Manager")
    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id){ return clientRepository.findById(id);}

    public Client create(Client client){
//        clientRepository.save(client);
        return clientRepository.save(client);
    }

    public Client editClieny(Client client){
        clientRepository.save(client);
        Long clientid = client.getId();
        Client updateClient = clientRepository.findById(clientid).get();
       return updateClient;
    }
    public void  delite(@PathVariable long id){
        clientRepository.deleteById(id);
    }

    public List<Client> getAllClientsByManager_id(Long id){
        return clientRepository.findAllByManager_Id(id);
    }

    public  Optional<Client> getClientByPhone(String phone){
        return clientRepository.findClientByPhone(phone);
    }
    public  List<Client> getClientsByAddress(String address){
        return clientRepository.findClientByAddress(address);
    }

    public  List<Client> getAllClientsByStatus(ClientStatus status){
        return clientRepository.findClientByStatus(status);
    }

    public List<Client> getAllClientsCreatedBetween(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo){
        return clientRepository.findClientByCreatedAtIsBetween(dateTimeWith, dateTimeTo);
    }
}
