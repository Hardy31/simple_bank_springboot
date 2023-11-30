package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс  ClientService
 *
 * @Service
 * @Transactional List<Client> findAllByManager_Id(Long id); использован построитель запросов по имени метода.
 * Поиск клиента по Id
 * <p>
 * Optional<Client> findClientByPhone(String phone); использован построитель запросов по имени метода.
 * Поиск клиента по телефону
 * <p>
 * List<Client> findClientByAddress(String address); использован построитель запросов по имени метода.
 * Поиск клиентов по адресу
 * <p>
 * List<Client> findClientByStatus(ClientStatus status); использован построитель запросов по имени метода.
 * Поиск клиентов по статусуадресу
 * <p>
 * List<Client> findClientByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );
 * использован построитель запросов по имени метода.
 * Возвращает список клиентов которые были созданы в промежутке переданных дат ( с по).
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-19
 */


@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Cacheable("Clients")
    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @CacheEvict("Clients")
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @CacheEvict("Clients")
    public Client editClieny(Client client) {
        clientRepository.save(client);
        Long clientid = client.getId();
        Client updateClient = clientRepository.findById(clientid).get();
        return updateClient;
    }

    @CacheEvict("Clients")
    public void delite(@PathVariable long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllClientsByManager_id(Long id) {
        return clientRepository.findAllByManager_Id(id);
    }

    /**
     * Возвращает Клиента по номеру телефона
     * (желательно реализовать проверку по маске)
     * @param  phone Стринг - номер телефона
     * @return Optional<Client>
     */
    public Optional<Client> getClientByPhone(String phone) {
        return clientRepository.findClientByPhone(phone);
    }

    /**
     * Возвращает список Клиентов (общий длоя членов семьи)
     * @param  address Стринговая  - адрес проживание клиента
     * @return List<Client> Список клиентов
     */
    public List<Client> getClientsByAddress(String address) {
        return clientRepository.findClientByAddress(address);
    }

    /**
     * Возвращает список Клиентов повыбранному статусу
     * @param  status Энум ClientStatus
     * @return List<Client> Список Клиентов
     */
    public List<Client> getAllClientsByStatus(ClientStatus status) {
        return clientRepository.findClientByStatus(status);
    }

    /**
     * Возвращает список Клиентов созданных с по
     * @param  dateTimeWith  с какой даты в формате LocalDateTime
     * @param  dateTimeTo   по какую дату в формате
     * @return List<Client>
     */
    public List<Client> getAllClientsCreatedBetween(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo) {
        return clientRepository.findClientByCreatedAtIsBetween(dateTimeWith, dateTimeTo);
    }
}
