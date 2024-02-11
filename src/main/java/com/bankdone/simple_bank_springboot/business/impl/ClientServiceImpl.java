package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.business.exeption.ClientNotFoundException;
import com.bankdone.simple_bank_springboot.business.exeption.ErrorMessage;
import com.bankdone.simple_bank_springboot.business.exeption.ManagerNotFoundException;
import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.dto.*;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import com.bankdone.simple_bank_springboot.mapper.ClientMapper;
import com.bankdone.simple_bank_springboot.mapper.ManagerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * Предоставляет методы для выполнения различных операций, связанных с клиентами.
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
 *
 * <p>
 * @автор Hardy
 * @версия 1.0
 * @от 2023-12-04
 */


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    /**
     * * ClientRepository: это поле используется для доступа к данным клиентов в базе данных.
     */
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private  final ClientMapper clientMapper;

    /**
     * Возвращает список Всех Клиентов
     * @return List<Client> Список клиентов
     */
    @CacheEvict("Clients")
    public List<ClientDTO> getAllClients() {
        List<Client> clientLise = clientRepository.findAll();
        log.info("ClientServiceImpl getAllClients() clientList - ", clientLise);
        List<ClientDTO> clientDTOLise  = new ClientListDTO(
                clientMapper.convertToClientDTOList(clientLise)).getClientDTOList();
        log.info("ClientServiceImpl getAllClients() clientDTOList - ", clientDTOLise);
        return clientDTOLise;
    }

    /**
     * возвращает  Клиента по id
     * @param  id Long - данные в обекте
     * @return Optional<Client>
     */
    @CacheEvict("Clients")
    public ClientDTO getClientById(Long id) {
        ClientDTO result = clientMapper.convertToDTO(clientRepository.findById(id).get());
        log.info("ClientServiceImpl getClientById(Long id) clientDTOLise - ", result);
        return result;

    }

    /**
     * Создает  Клиента по переданным параметрам
     * @param  clientCreatDTO Сlient - данные в обекте
     * @return ClientDTO
     */
    @CacheEvict("Clients")
    public ClientDTO create(ClientCreatDTO clientCreatDTO) {

        log.info("ClientServiceImpl create(clientCreatDTO clientCreateDTO) {}", clientCreatDTO);
        String managerID = clientCreatDTO.getManagerID();
        Long managerId = Long.parseLong(managerID);
        Manager manager = managerRepository.findById(managerId).orElseThrow(
                ()->new ManagerNotFoundException(ErrorMessage.Manager_NOT_FOUND)
        );

        Client toEntity = clientMapper.createToEntity(clientCreatDTO);
        toEntity.setManager(manager);
        log.info("ClientServiceImpl create(clientCreatDTO toEntity) {}", toEntity);

        Client save = clientRepository.save(toEntity);
        ClientDTO requestDto = clientMapper.convertToDTO(save);
        return requestDto;
    }

    /**
     * редактирует  Клиента по переданным параметрам
     * @param  clientDTO Сlient - данные в обекте
     * @return Client
     */
    @CacheEvict("Clients")
    public ClientDTO editClieny(ClientDTO clientDTO) {
        Client client = clientMapper.convertToEntity( clientDTO);
        client.setCreatedAt(clientRepository.findById(client.getId()).get().getCreatedAt());
        client.setUpdatedAt(LocalDateTime.now());
        Client savedClient1 = clientRepository.save(client);
        Long clientId = savedClient1.getId();
        Client requestClient = clientRepository.findById(clientId).orElseThrow(
                ()->new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND)
        );
        return  clientMapper.convertToDTO(requestClient);
    }

    /**
     * Удоляет Клиента по id
     * @param  id Long
     */
    @CacheEvict("Clients")
    public void delite(@PathVariable long id) {
        Optional<Client> clientDTO = clientRepository.findById(id);
        if (clientDTO.isPresent())
            clientRepository.deleteById(id);
        else throw new ManagerNotFoundException(ErrorMessage.Manager_NOT_FOUND);
    }

    public List<ClientDTO> getAllClientsByManager_id(Long id) {

        List<ClientDTO> clientDTOLise  = new ClientListDTO(
                clientMapper.convertToClientDTOList(
                        clientRepository.findAllByManager_Id(id)
                )
        ).getClientDTOList();
        return clientDTOLise;
    }

    /**
     * Возвращает Клиента по номеру телефона
     * TODO: проверка номера по маске
     * @param  phone Стринг - номер телефона
     * @return Optional<Client>
     */
    public ClientDTO getClientByPhone(String phone) {
        ClientDTO clientDTO = clientMapper.convertToDTO(
                clientRepository.findClientByPhone(phone)
                        .get()
        );
        return clientDTO;
    }

    /**
     * Возвращает список Клиентов (общий длоя членов семьи)
     * @param  address Стринговая  - адрес проживание клиента
     * @return List<Client> Список клиентов
     */
    public List<ClientDTO> getClientsByAddress(AdressDTO address) {
        List<ClientDTO> clientDTOLise  = new ClientListDTO(
                clientMapper.convertToClientDTOList(
                        clientRepository.findClientByAddress(address.getAdress())
                )
        ).getClientDTOList();

        return clientDTOLise;
    }

    /**
     * Возвращает список Клиентов повыбранному статусу
     * @param  status Энум ClientStatus
     * @return List<Client> Список Клиентов
     */
    public List<ClientDTO> getAllClientsByStatus(ClientStatus status) {
        List<ClientDTO> clientDTOLise  = new ClientListDTO(
                clientMapper.convertToClientDTOList(
                        clientRepository.findClientByStatus(status)
                )
        ).getClientDTOList();
        return clientDTOLise;
    }

    /**
     * Возвращает список Клиентов созданных с по
     * @param  periodDTO  имеет  две даты  ( от и до)
     * @return List<Client>
     */
    public List<ClientDTO> getAllClientsCreatedBetween(PeriodDTO periodDTO) {
        LocalDateTime  dateTimeWith =  periodDTO.getFromDate();
        LocalDateTime dateTimeTo = periodDTO.getBeforeDate();
        List<ClientDTO> clientDTOLise  = new ClientListDTO(
                clientMapper.convertToClientDTOList(
                        clientRepository.findClientByCreatedAtIsBetween(dateTimeWith, dateTimeTo)
                )
        ).getClientDTOList();
        return clientDTOLise;
    }
}
