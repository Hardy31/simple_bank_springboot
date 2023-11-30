package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * <h3>Класс  ManagerController</h3><br>
 *
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Manager.<br>
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
@RequestMapping("/rest")
@RequiredArgsConstructor
public class ManagerController {

    /**
     * объект ManagerService для получения результата запроса из сервис слоя
     */
    private final ManagerService managerService;

    /**
     * При отправке Get запроса на  URN rest/managers/all
     * возвращает список всех менеджеров <br>
     * http://localhost:8080/rest/managers/all
     */
    @GetMapping("managers/all")
    public List<Manager> getAllManagers() {
        log.info("ManagerService /rest/managers/all ");
        return managerService.getAllManagers();
    }

    /**
     * При отправке Get запроса на  URN rest/managerStatus/{status}
     * возвращает список всех менеджеров со статусом переданным в поле статус
     * @return List<Manager> <br>
     * http://localhost:8080/rest/managerStatus/ACTIVE <br>
     * http://localhost:8080/rest/managerStatus/BUSINESS_TRIP
     */
    @GetMapping("managerStatus/{status}")
    public List<Manager> getAllManagersByStatus(@PathVariable String status) {
        return managerService.getAllManagersByStatus(ManagerStatus.valueOf(status));
    }

    /**
     * При отправке Get запроса на  URN rest/manager/{id}
     * возвращает менеджара с соответствующим ID <br>
     * http://localhost:8080/rest/manager/5
     */
    @GetMapping("manager/{id}")
    public Optional<Manager> getManagerById(@PathVariable Long id) {
        log.info("AgreementController//rest/agreement/{id} : " + id);
        return managerService.getManagerById(id);
    }

    /**
     * При отправке Delete запроса на  URN rest/deleteManager/{id}
     * возвращает менеджара с соответствующим ID <br>
     * http://localhost:8080/rest/deleteManager/id?id=30
     */
    @DeleteMapping("deleteManager/{id}")
    public void delete(Long id) {
        log.info("ManagerService /rest/deleteManager/{id} : " + id);
        managerService.deleteById(id);
    }


    /**
     * При отправке Post запроса на  URN rest/createManager
     * возвращает созданный объект Manager <br>
     * http://localhost:8080/rest/deleteManager
     */
    @PostMapping("createManager")
    public Manager save(@RequestBody Manager manager) {
        log.info("ManagerService /rest/createManager : " + manager);
        return managerService.create(manager);
    }

    /**
     * При отправке Put запроса на  URN rest/editManager/{id}
     * возвращает измененный  объект Manager , поиск по id <br>
     * http://localhost:8080/rest/editManager/5
     */
    @PutMapping("editManager/{id}")
    public Manager edit(@PathVariable Long id, @RequestBody Manager manager) {
        log.info("ManagerService /rest/editManager/{id} + Manager : " + id + " + " + manager);
        return managerService.editManager(id, manager);
    }

    /**
     * При отправке get запроса на  URN rest/ManagersWorkingWith/{data}
     * возвращает список менеджеров , работающих с <br>
     *     http://localhost:8080/rest/ManagersWorkingWith/2023-07-14
     */
    @GetMapping("ManagersWorkingWith/{data}")
    public List<Manager> getAllManagersWorkingWith(@PathVariable String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDate.parse(data, formatter).atStartOfDay();
        return managerService.getAllManagersWorkingWith(dateTime);
    }

    /**
     * При отправке get запроса на  URN rest/MManagersWorkingWith/{dataWith}/to/{dataTo}
     * возвращает список менеджеров , работающих с по (LocalData) <br>
     * http://localhost:8080/rest/ManagersWorkingWithTo/2023-07-14/to/2023-07-16 <br>
     * http://localhost:8080/rest/ManagersWorkingWithTo/1998-07-08/to/1998-07-20
     */
    @GetMapping("ManagersWorkingWith/{dataWith}/to/{dataTo}")
    public List<Manager> getAllManagersWorkingWithTo(@PathVariable String dataWith, @PathVariable String dataTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTimeWith = LocalDate.parse(dataWith, formatter).atStartOfDay();
        LocalDateTime dateTimeTo = LocalDate.parse(dataTo, formatter).atStartOfDay();
        return managerService.getAllManagersWorkingWithTo(dateTimeWith, dateTimeTo);
    }

    /**
     * При отправке Post запроса на  URN rest/getManagerFIO
     * возвращает  Manager , поиск  по ФИО <br>
     * http://localhost:8080/rest/getManagerFIO
     */
    @PostMapping("getManagerFIO")
    public Manager getManagerByNameSurname(@RequestBody Manager manager) {
        Manager managerdb = managerService.getManagersByFIO(manager);
        return managerdb;
    }

}
