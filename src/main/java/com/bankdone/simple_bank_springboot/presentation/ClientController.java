package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ClientService;
import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс  ManagerController
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet.
 *
 * public List<Client> geAllClients()  - Возвращаетсписок Всех Клиентов.
 * public Client getById(@PathVariable Long id) - Возвращает Клиента по Id
 * public List<Client> getClientService(@PathVariable Long id) - возвращает Всех клиентов  по Id Mенеджера.
 * public Client geClientByPhone(@PathVariable String phone) возвращает Слиента по номеру телефона.
 * public List<Client> geClientByAddress(@RequestBody Client client) возвращает Клиентов по Адресу проживания.
 * public List<Client> getAllClientStatus(@RequestBody Client client) возвращает список Клиентов по выбранному статусу.
 * public  Client create(@RequestBody Client client) создание Клиента.
 * public Client editClient(@RequestBody Client client) редактирование Клиента.
 * public void getClientById(@PathVariable Long id) возвращает Клиента по Id.
 *
 *
 * @RestController (@ Controller + @ ResponsBody) указывает, что этот класс является контроллером,
 * обрабатывающим HTTP-запросы.
 * @RequestMapping("/rest") - указывает базовый путь URL-адреса для всех конечных точек в этом контроллере. который будет "/rest".
 * @Autowired в Spring Framework используется для автоматического связывания компонентов бина между собой.
 * По умолчанию скоуп бина - синглтон.
 * @GetMapping("managers") используется для сопоставления HTTP-запросов GET с определенными методами в классе контроллера.
 * Он указывает URL-путь для конечной точки и определяет логику обработки запроса GET и генерации ответа.
 * Metod public List<ManagerEntity> getManagerEntitys()  возвращает список всех ManagerEntity в JSON формате
 *
 *
 *
 * @автор  Hardy
 * @версия 1.0
 * @от 2023-11-09
 */

@RestController
@RequestMapping("/rest")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("clients/all")
    //    http://localhost:8080/rest/clients/all
    public List<Client> geAllClients() {
        return clientService.getAllClients().stream().map(Client::toClient).collect(Collectors.toList());
    }

    @GetMapping("client/{id}")
    //    http://localhost:8080/rest/client/5
    public Client getById(@PathVariable Long id) {
        return clientService.getClientById(id).get();
    }

    @GetMapping("getAllClientsByManager_id/{id}")
    //    http://localhost:8080/rest/getAllClientsByManager_id/2
    public List<Client> getClientService(@PathVariable Long id) {
        return clientService.getAllClientsByManager_id(id);
    }

    @GetMapping("getClientByPhone/{phone}")
    //    http://localhost:8080/rest/getClientByPhone/+7(321) 123-45-69
    public Client geClientByPhone(@PathVariable String phone){
        return clientService.getClientByPhone(phone).get();
    }

    @PostMapping("getClientByAddress")
    //    http://localhost:8080/rest/getClientByAddress
    public List<Client> geClientByAddress(@RequestBody Client client){
        return clientService.getClientsByAddress(client.getAddress());
    }

    @PostMapping("getClientByStatus")
    //    http://localhost:8080/rest/getClientByStatus
    public List<Client> getAllClientStatus(@RequestBody Client client){
        ClientStatus requestClientStatus = client.getStatus();
        return clientService.getAllClientsByStatus(requestClientStatus);
    }

    @GetMapping("AllClientsCreatedWith/{dataWith}/to/{dataTo}")
    //    http://localhost:8080/rest/AllClientsCreatedWith/2023-11-14/to/2023-11-16
    public List<Client> getAllClientsCreatedWithTo(@PathVariable String dataWith, @PathVariable String dataTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTimeWith = LocalDate.parse(dataWith, formatter).atStartOfDay();
        LocalDateTime dateTimeTo = LocalDate.parse(dataTo, formatter).atStartOfDay();
        return clientService.getAllClientsCreatedBetween(dateTimeWith, dateTimeTo);
    }

    @PostMapping("createClient")
    //    http://localhost:8080/rest/createClient
    public  Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PutMapping("editClient/")
    //    http://localhost:8080/rest/editClient
    public Client editClient(@RequestBody Client client){
        return clientService.editClieny( client);
    }

    @DeleteMapping("deleteClient/{id}")
    //    http://localhost:8080/rest/deleteClient/6
    public void getClientById(@PathVariable Long id){
        clientService.delite(id);
    }


}
