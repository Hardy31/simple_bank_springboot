package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.dto.AdressDTO;
import com.bankdone.simple_bank_springboot.dto.ClientCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ClientDTO;
import com.bankdone.simple_bank_springboot.dto.PeriodDTO;
import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>Класс  ClientController</h3><br>
 *
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Client.<br>
 *
 *  - Аннотация RestController (@Controller + @ ResponsBody) указывает, что этот класс является REST контроллером, <br>
 *  = Аннотация RequestMapping("/rest") - указывает базовый путь URL-адреса для всех конечных точек
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-09
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/clients")
public class ClientController {

    /**
     * объект ClientService для получения результата запроса из сервис слоя
     */
    private final ClientService clientService;

    /**
     * При отправке Get запроса на  URN rest/clients
     * возвращает список всех клиентов <br>
     * http://localhost:8080/rest/clients
     */
    @GetMapping("")
    public List<ClientDTO> geAllClients() {
        log.info("ClientController geAllClients");
        return clientService.getAllClients();
    }

    /**
     * При отправке Get запроса на URN rest/clients/{id}
     * возвращает клиента по переданному id <br>
     * http://localhost:8080/rest/clients/5
     */
    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id) {
        log.info("ClientController getById = {}", id);
        return clientService.getClientById(id);
    }

    /**
     * При отправке Get запроса на  URN rest/clients/by-manager/{id}
     * возвращает клиента по переданному id <br>
     * http://localhost:8080/rest/clients/by-manager/2
     */
    @GetMapping("/by-manager/{id}")
    public List<ClientDTO> getClientByManagerId(@PathVariable Long id) {
        log.info("ClientController getClientsByManagerId = {}", id);
        return clientService.getAllClientsByManager_id(id);
    }

    /**
     * При отправке Get запроса на  URN rest/clients/by-phone/{phone}
     * возвращает клиента по переданному номеру телефона <br>
     * http://localhost:8080/rest/clients/by-phone/+7(321) 123-45-69
     */
    @GetMapping("/by-phone/{phone}")
    public ClientDTO geClientByPhone(@PathVariable String phone) {
        log.info("ClientController getClientByPhone = {}", phone);
        return clientService.getClientByPhone(phone);
    }

    /**
     * При отправке Post запроса на  URN rest/clients/by-address
     *  возвращает список клиентов  по переданному в теле метода адресу <br>
     *  http://localhost:8080/rest/clients/by-address
     */
    @PostMapping("/by-address") //данный запрос не соответствует REST Full. , был реализован для пробы реализации
    public List<ClientDTO> geClientByAddress(@RequestBody AdressDTO adressDTO) {
        log.info("ClientController getClientsByAddress = {}", adressDTO);
        return clientService.getClientsByAddress(adressDTO);
    }

    /**
     * При отправке Post запроса на  URN rest/clients/by-status
     *  возвращает список клиентов  по переданному в теле метода статусу <br>
     *  http://localhost:8080/rest/clients/by-status
     */
    @PostMapping("/by-status")  //данный запрос не соответствует REST Full. , был реализован для пробы реализации
    public List<ClientDTO> getAllClientByStatus(@RequestBody ClientDTO clientDTO) {
        ClientStatus requestClientStatus = ClientStatus.valueOf(clientDTO.getStatus());
        log.info("ClientController getAllClientsByStatus = {}", requestClientStatus);
        return clientService.getAllClientsByStatus(requestClientStatus);
    }

    /**
     * При отправке Get запроса на  URN rest/clients/by-period
     *  возвращает список клиентов  c с периодом создания  с - по  <br>
     *  http://localhost:8080/rest/clients/by-period
     */
    @PostMapping("/by-period") //данный запрос не соответствует REST Full. , был реализован для пробы реализации
    public List<ClientDTO> getAllClientsCreatedWithTo(@RequestBody PeriodDTO periodDTO) {
        log.info("ClientController getAllClientsCreatedWithTo periodDTO = {}  ",
                periodDTO
        );
//        return clientService.getAllClientsCreatedBetween(
//                periodDTO.getFromDate(),
//                periodDTO.getBeforeDate()
//        );


        return clientService.getAllClientsCreatedBetween(periodDTO);
    }

    /**
     * При отправке Post запроса на  URN rest/clients
     *  сохраняет в БД и возвращает созданного Клиента <br>
     *  http://localhost:8080/rest/clients
     */
    @PostMapping("")
    public ClientDTO create(@RequestBody ClientCreatDTO clientCreatDTO) {
        log.info("ClientController create  = {}", clientCreatDTO);
        return clientService.create(clientCreatDTO);
    }

    /**
     * При отправке Put запроса на  URN rest/clients
     * редактирует  найденного по id  в БД и возвращает новый <br>
     * http://localhost:8080/rest/clients
     */
    @PutMapping("")
    public ClientDTO editClient(@RequestBody ClientDTO clientDTO) {
        log.info("ClientController editClient  = {}", clientDTO);
        return clientService.editClieny(clientDTO);
    }

    /**
     * При отправке delete запроса на  URN rest/clients/{id}
     * удаляет из Бд  по id <br>
     * http://localhost:8080/rest/clients/6
     */
    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Long id) {
        log.info("ClientController deleteClientById = {}", id);
        clientService.delite(id);
    }
}
