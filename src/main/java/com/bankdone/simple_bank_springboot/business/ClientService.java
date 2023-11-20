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

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    private ManagerRepository managerRepository;
    private Client updatedClient;
    @Cacheable("Manager")
    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id){ return clientRepository.findById(id);}

    public Client create(Client client){
//        Manager manager = managerRepository.findById(client.getManager().getId()).get();
//        client.setManager(manager);
//        System.out.println(client);
        clientRepository.save(client);
        return clientRepository.save(client);
    }

//    public Client editClieny(long id, Client client){
    public Client editClieny(Client client){
        Client updateClient = new Client();
        clientRepository.save(client);
        Long clientid = client.getId();
        updateClient = clientRepository.findById(clientid).get();
        System.out.println(updateClient);
       return updateClient;

//        Manager updateManager = new Manager();
//        managerRepository.save(manager);
//        updateManager = managerRepository.findById(id).get();
//        return updateManager;
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
