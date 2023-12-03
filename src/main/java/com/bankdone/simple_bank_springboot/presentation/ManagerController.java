package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.DTO.PeriodDTO;
import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * <h3>Класс  ManagerController</h3><br>
 * <p>
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Manager.<br>
 * <p>
 * - Аннотация RestController (@Controller + @ ResponsBody) указывает, что этот класс является REST контроллером, <br>
 * = Аннотация RequestMapping("/rest") - указывает базовый путь URL-адреса для всех конечных точек
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-09
 */


@Slf4j
@RestController
@RequestMapping("/rest/managers")
@RequiredArgsConstructor
public class ManagerController {

    /**
     * объект ManagerService для получения результата запроса из сервис слоя
     */
    private final ManagerService managerService;

    /**
     * При отправке Get запроса на  URN rest/managers/all
     * возвращает список всех менеджеров <br>
     * http://localhost:8080/rest/managers
     */
    @GetMapping("")
    public List<Manager> getAllManagers() {
        log.info("ManagerController - getAllManagers() ");
        return managerService.getAllManagers();
    }

    /**
     * При отправке Get запроса на  URN rest/managerStatus/{status}
     * возвращает список всех менеджеров со статусом переданным в поле статус
     *
     * @return List<Manager> <br>
     * http://localhost:8080/rest/managers/status/ACTIVE <br>
     * http://localhost:8080/rest/managers/status/BUSINESS_TRIP
     */
    @GetMapping("/status/{status}")
    public List<Manager> getAllManagersByStatus(@PathVariable String status) {
        log.info("ManagerController getAllManagersByStatus : {}", status);
        return managerService.getAllManagersByStatus(ManagerStatus.valueOf(status));
    }

    /**
     * При отправке Get запроса на  URN rest/manager/{id}
     * возвращает менеджара с соответствующим ID <br>
     * http://localhost:8080/rest/managers/5
     */
    @GetMapping("/{id}")
    public Optional<Manager> getManagerById(@PathVariable Long id) {
        log.info("ManagerController getManagerById : {} ", id);
        return managerService.getManagerById(id);
    }

    /**
     * При отправке Delete запроса на  URN rest/deleteManager/{id}
     * возвращает менеджара с соответствующим ID <br>
     * http://localhost:8080/rest/managers/43
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("ManagerController delete : {}", id);
        managerService.deleteById(id);
    }


    /**
     * При отправке Post запроса на  URN rest/createManager
     * возвращает созданный объект Manager <br>
     * http://localhost:8080/rest/managers
     */
    @PostMapping("")
    public Manager create(@RequestBody Manager manager) {
        log.info("ManagerController create : {}", manager);
        return managerService.create(manager);
    }

    /**
     * При отправке Put запроса на  URN rest/editManager/{id}
     * возвращает измененный  объект Manager , поиск по id <br>
     * http://localhost:8080/rest/managers/43
     */
    @PutMapping("/{id}")
    public Manager edit(@PathVariable Long id, @RequestBody Manager manager) {
        log.info("ManagerController edit(@PathVariable Long id = {} , @RequestBody Manager manager = {}) : ", id, manager);
        return managerService.editManager(id, manager);
    }

    /**
     * При отправке get запроса на  URN rest/ManagersWorkingWith/{data}
     * возвращает список менеджеров , работающих с <br>
     * http://localhost:8080/rest/managers/with/2023-07-27T00:00
     */
    @GetMapping("/with/{data}")
    public List<Manager> getAllManagersWorkingWith(
            @PathVariable(value = "data")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime data) {
        log.info("ManagerController getAllManagersWorkingWith = {}", data);
        return managerService.getAllManagersWorkingWith(data);
    }



    /**
     * При отправке get запроса на  URN rest/managers/with/{dataWith}/to/{dataTo}
     * возвращает список менеджеров , работающих с по (LocalDateTime) <br>
     * http://localhost:8080/rest/mabagers/with/1998-06-08T00:00/to/1998-07-21T00:00<br>
     *
     * Эквивалентный метод :
     * @PostMapping("/by-period")
     * public List<Manager> getAllManagersByPeriod(@RequestBody PeriodDTO periodDTO)
     */
    @GetMapping("with/{dateWith}/to/{dateTo}")
    public List<Manager> getAllManagersWorkingWithTo(
            @PathVariable (value = "dateWith")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateWith,
            @PathVariable (value = "dateTo")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTo
            ) {
        log.info("ManagerController getAllManagersWorkingWith = {} to = {}", dateWith, dateTo);
        return managerService.getAllManagersWorkingWithTo(dateWith, dateTo);
    }

    /**
     * При отправке Post запроса на  URN rest/getManagerFIO
     * возвращает  Manager , поиск  по ФИО <br>
     * http://localhost:8080/rest/getManagerFIO
     */
    @PostMapping("/by-FIO")
    public Manager getManagerByNameSurname(@RequestBody Manager manager) {
        Manager managerdb = managerService.getManagersByFIO(manager);
        log.info("ManagerController getManagerByNameSurname = {} ", manager);
        return managerdb;
    }


    /**
     * При отправке post запроса на  URN rest/managers/by-period
     * возвращает список менеджеров , работающих с по (LocalDateTime) <br>
     * добавлен конструктор (LocalDate) <br>
     * http://localhost:8080/rest/mabagers//by-period<br>
     *
     * Эквивалентный метод :
     *     @GetMapping("with/{dateWith}/to/{dateTo}")
     *     public List<Manager> getAllManagersWorkingWithTo(
     */
    @PostMapping("/by-period")
    public List<Manager> getAllManagersByPeriod(@RequestBody PeriodDTO periodDTO)
    {
        log.info("ManagerController getAllManagersByPeriod fromDate = {} befoDate = {} ",
                periodDTO.getFromDate(),
                periodDTO.getBeforeDate()
        );
        return managerService.getAllManagersWorkingWithTo(
                periodDTO.getFromDate().toLocalDateTime(),
                periodDTO.getBeforeDate().atStartOfDay()
        );
    }
}
