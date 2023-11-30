package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class ClientController {

    /**
     * объект ClientService для получения результата запроса из сервис слоя
     */
    private final ClientService clientService;

    /**
     * При отправке Get запроса на  URN rest/clients/all
     * возвращает список всех клиентов <br>
     * http://localhost:8080/rest/clients/all
     */
    @GetMapping("clients/all")
    public List<Client> geAllClients() {
        return clientService.getAllClients().stream().map(Client::toClient).collect(Collectors.toList());
    }

    /**
     * При отправке Get запроса на URN rest/client/{id}
     * возвращает клиента по переданному id <br>
     * http://localhost:8080/rest/client/5
     */
    @GetMapping("client/{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getClientById(id).get();
    }

    /**
     * При отправке Get запроса на  URN rest/getAllClientsByManager_id/{id}
     * возвращает клиента по переданному id <br>
     * http://localhost:8080/rest/getAllClientsByManager_id/2
     */
    @GetMapping("getAllClientsByManager_id/{id}")
    public List<Client> getClientService(@PathVariable Long id) {
        return clientService.getAllClientsByManager_id(id);
    }

    /**
     * При отправке Get запроса на  URN rest/getClientByPhone/{phone}
     * возвращает клиента по переданному номеру телефона <br>
     * http://localhost:8080/rest/getClientByPhone/+7(321) 123-45-69
     */
    @GetMapping("getClientByPhone/{phone}")
    public Client geClientByPhone(@PathVariable String phone) {
        return clientService.getClientByPhone(phone).get();
    }

    /**
     * При отправке Post запроса на  URN rest/getClientByAddress
     *  возвращает список клиентов  по переданному в теле метода адресу <br>
     *  http://localhost:8080/rest/getClientByAddress
     */
    @PostMapping("getClientByAddress")
    public List<Client> geClientByAddress(@RequestBody Client client) {
        return clientService.getClientsByAddress(client.getAddress());
    }

    /**
     * При отправке Post запроса на  URN rest/getClientByStatus
     *  возвращает список клиентов  по переданному в теле метода статусу <br>
     *  http://localhost:8080/rest/getClientByStatus
     */
    @PostMapping("getClientByStatus")
    public List<Client> getAllClientStatus(@RequestBody Client client) {
        ClientStatus requestClientStatus = client.getStatus();
        return clientService.getAllClientsByStatus(requestClientStatus);
    }

    /**
     * При отправке Get запроса на  URN rest/AllClientsCreatedWith/{dataWith}/to/{dataTo}
     *  возвращает список клиентов  c с периодом создания  с - по  <br>
     *  http://localhost:8080/rest/AllClientsCreatedWith/2023-11-14/to/2023-11-16
     */
    @GetMapping("AllClientsCreatedWith/{dataWith}/to/{dataTo}")
    public List<Client> getAllClientsCreatedWithTo(@PathVariable String dataWith, @PathVariable String dataTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTimeWith = LocalDate.parse(dataWith, formatter).atStartOfDay();
        LocalDateTime dateTimeTo = LocalDate.parse(dataTo, formatter).atStartOfDay();
        return clientService.getAllClientsCreatedBetween(dateTimeWith, dateTimeTo);
    }

    /**
     * При отправке Post запроса на  URN rest/createClient
     *  сохраняет в БД и возвращает созданного Клиента <br>
     *  http://localhost:8080/rest/createClient
     */
    @PostMapping("createClient")
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    /**
     * При отправке Put запроса на  URN rest/editClient
     * редактирует  найденного по id  в БД и возвращает новый <br>
     * http://localhost:8080/rest/editClient
     */
    @PutMapping("editClient/")
    public Client editClient(@RequestBody Client client) {
        return clientService.editClieny(client);
    }

    /**
     * При отправке delete запроса на  URN rest/deleteClient/{id}
     * удаляет из Бд  по id <br>
     * http://localhost:8080/rest/deleteClient/6
     */
    @DeleteMapping("deleteClient/{id}")
    public void getClientById(@PathVariable Long id) {
        clientService.delite(id);
    }


}
