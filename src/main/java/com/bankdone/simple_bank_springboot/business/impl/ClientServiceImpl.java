package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс  ClientServiceImpl является реализацией интерфейса ClientService.
 * Предоставляет методы для выполнения различных операций, связанных с менеджерами.
 *
 * @Service: эта аннотация используется для указания того, что этот класс является компонентом службы в среде Spring.
 * @RequiredArgsConstructor: эта аннотация взята из библиотеки Lombok и генерирует конструктор с обязательными аргументами.
 * для последних полей. Это позволяет нам внедрять зависимости с помощью внедрения конструктора (взамен @Autowired).
 * @Slf4j: Эта аннотация взята из библиотеки Lombok и генерирует поле для логирования.
 * @Transactional: эта аннотация используется в Spring для определения границ транзакций для методов или классов.
 * При применении к методу или классу это указывает на то, что для аннотированного метода необходимо создать транзакцию.
 * или все методы внутри аннотированного класса.
 * Транзакционные границы гарантируют, что группа операций выполняется как одна атомарная единица.
 * Важно отметить, что аннотацию @Transactional следует применять к методам, изменяющим данные.
 * или выполнить несколько операций с базой данных, чтобы обеспечить целостность и согласованность данных.
 * <p>
 * @автор Hardy
 * @версия 1.0
 * @от 2023-12-04
 */


@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    /**
     * * ClientRepository: это поле используется для доступа к данным клиентов в базе данных.
     */
    private final ClientRepository clientRepository;

    /**
     * Возвращает список Всех Клиентов
     * @return List<Client> Список клиентов
     */
    @Cacheable("Clients")
    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    /**
     * возвращает  Клиента по id
     * @param  id Long - данные в обекте
     * @return Optional<Client>
     */
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    /**
     * Создает  Клиента по переданным параметрам
     * @param  client Сlient - данные в обекте
     * @return Client
     */
    @CacheEvict("Clients")
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    /**
     * редактирует  Клиента по переданным параметрам
     * @param  client Сlient - данные в обекте
     * @return Client
     */
    @CacheEvict("Clients")
    public Client editClieny(Client client) {
        clientRepository.save(client);
        Long clientid = client.getId();
        Client updateClient = clientRepository.findById(clientid).get();
        return updateClient;
    }

    /**
     * Удоляет Клиента по id
     * @param  id Long
     */
    @CacheEvict("Clients")
    public void delite(@PathVariable long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllClientsByManager_id(Long id) {
        return clientRepository.findAllByManager_Id(id);
    }

    /**
     * Возвращает Клиента по номеру телефона
     * TODO: проверка номера по маске
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
