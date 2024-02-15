package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerListDTO;
import com.bankdone.simple_bank_springboot.dto.PeriodDTO;
import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.v3.oas.annotations.OpenAPI31;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
//@Api (description = "ManagerController  пример работы Swagger")
public class ManagerController {

    /**
     * объект ManagerService для получения результата запроса из сервис слоя
     */
    private final ManagerService managerService;

    /**
     * При отправке Get запроса на  URN rest/managers
     * возвращает список всех менеджеров <br>
     * http://localhost:8080/rest/managers
     */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("Получение списка всех Менеджеров")
    public List<ManagerDTO> getAllManagers() {
        log.info("ManagerController - getAllManagers() ");
        return managerService.getAllManagers();
    }

    /**
     * При отправке Get запроса на  URN rest/manager/{status}
     * возвращает список всех менеджеров со статусом переданным в поле статус
     *
     * @return List<Manager> <br>
     * http://localhost:8080/rest/managers/status/ACTIVE <br>
     * http://localhost:8080/rest/managers/status/BUSINESS_TRIP
     */
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("Получение списка всех Менеджеров со статусом ???")
    public List<ManagerDTO> getAllManagersByStatus(@PathVariable String status) {
        log.info("ManagerController getAllManagersByStatus(@PathVariable String status) : {}", status);
        return managerService.getAllManagersByStatus(ManagerStatus.valueOf(status));
    }

    /**
     * При отправке Get запроса на  URN rest/managers/{id}
     * возвращает менеджара с соответствующим ID <br>
     * http://localhost:8080/rest/managers/5
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("Получение Менеджера по его Id")
    public ManagerDTO getManagerById(@PathVariable Long id) {
        log.info("ManagerController getManagerById(@PathVariable Long id) : {} ", id);
        return managerService.getManagerById(id);
    }

    /**
     * При отправке Delete запроса на  URN rest/managers/{id}
     * возвращает менеджара с соответствующим ID <br>
     * http://localhost:8080/rest/managers/43
     */
    @DeleteMapping("/{id}")
//    @ApiOperation("Удаление Менеджера по его Id")
    public void delete(@PathVariable Long id) {
        log.info("ManagerController delete(@PathVariable Long id) : {}", id);
        managerService.deleteManagerById(id);
    }


    /**
     * При отправке Post запроса на  URN rest/managers
     * возвращает созданный объект Manager <br>
     * http://localhost:8080/rest/managers
     */
    @PostMapping("")
//    @ApiOperation("Создание Менеджера ")
    public ManagerDTO create(@RequestBody ManagerCreatDTO managerDTO) {
        log.info("ManagerController create(@RequestBody Manager manager!!!!!) : {}", managerDTO);
        ManagerDTO responsManagerDTO = managerService.createManager(managerDTO);
        log.info("ManagerController create(@RequestBody Manager !!!!!!!!!!!!manager!!!!!) : {}", responsManagerDTO);
        return responsManagerDTO;
    }

    /**
     * При отправке Put запроса на  URN rest/editManager/{id}
     * возвращает измененный  объект Manager , поиск по id <br>
     * http://localhost:8080/rest/managers/43
     */
    @PutMapping("/{id}")
//    @ApiOperation("Изменение данных в модели Менеджер по его Id")
    public ManagerDTO edit(@PathVariable Long id, @RequestBody ManagerDTO managerDTO) {
        log.info("ManagerController edit(" +
                "@PathVariable Long id = {} , " +
                "@RequestBody Manager manager = {}) : ",
                id,
                managerDTO
        );
        return managerService.editManager(id, managerDTO);
    }

    /**
     * При отправке get запроса на  URN rest/ManagersWorkingWith/{data}
     * возвращает список менеджеров , работающих с даты <br>
     * http://localhost:8080/rest/managers/with/2023-07-27T00:00
     */
    @GetMapping("/with/{data}")
//    @ApiOperation("Получение списка всех Менеджеров работающих с определенной даты")
    public List<ManagerDTO> getAllManagersWorkingWith(
            @PathVariable(value = "data")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime data) {
        log.info("ManagerController getAllManagersWorkingWith = {}", data);
        return managerService.getAllManagersWorkingWith(data);
    }

    /**
     * При отправке Post запроса на  URN rest/managers/by-FIO
     * возвращает  Manager , поиск  по ФИО <br>
     * http://localhost:8080/rest/managers/by-FIO
     */
    @PostMapping("/by-FIO")
//    @ApiOperation("Получение списка всех Менеджеров по его фамилии")
    public String getManagerByNameSurname(@RequestBody ManagerDTO managerDTO) {

        log.info("ManagerController getManagerByNameSurname(@RequestBody Manager manager) : {} ", managerDTO);
        return managerService.getManagersByFIO(managerDTO);
    }


    /**
     * При отправке post запроса на  URN rest/managers/by-period
     * возвращает список менеджеров , были приняты на работу  с по (LocalDateTime) <br>
     * добавлен конструктор (LocalDate) <br>
     * http://localhost:8080/rest/mabagers//by-period<br>
     *
     * Эквивалентный метод :
     *     @GetMapping("with/{dateWith}/to/{dateTo}")
     *     public List<Manager> getAllManagersWorkingWithTo(
     */
    @PostMapping("/by-period")
//    @ApiOperation("Получение списка всех Менеджеров работавщих в период ????")
    public List<ManagerDTO> getAllManagersByPeriod(@RequestBody PeriodDTO periodDTO)
    {
        log.info("ManagerController etAllManagersByPeriod(@RequestBody PeriodDTO periodDTO) " +
                "fromDate = {} befoDate = {} ",
                periodDTO.getFromDate(),
                periodDTO.getBeforeDate()
        );
        return managerService.getAllManagersWorkingWithTo(
//                periodDTO.getFromDate().toLocalDateTime(),
                periodDTO.getFromDate(),
                periodDTO.getBeforeDate()
        );
    }

    /**
     * При отправке get запроса на  URN rest/managers/with/{dataWith}/to/{dataTo}
     * возвращает список менеджеров , были приняты на работу с по (LocalDateTime) <br>
     * http://localhost:8080/rest/mabagers/with/1998-06-08T00:00/to/1998-07-21T00:00<br>
     *
     * Эквивалентный метод :
     * @PostMapping("/by-period")
     * public List<Manager> getAllManagersByPeriod(@RequestBody PeriodDTO periodDTO)
     */
    @GetMapping("with/{dateWith}/to/{dateTo}")
//    @ApiOperation("Получение списка всех Менеджеров устрившихся на работу в период С По")
    public List<ManagerDTO> getAllManagersCreatAtWithTo(
            @PathVariable (value = "dateWith")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateWith,
            @PathVariable (value = "dateTo")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTo
    ) {
        log.info("ManagerController getAllManagersWorkingWith = {} to = {}", dateWith, dateTo);

        return managerService.getAllManagersWorkingWithTo(dateWith, dateTo);
    }
}
