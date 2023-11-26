package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Класс  ManagerController
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet.
 *
 * @RestController (@ Controller + @ ResponsBody) указывает, что этот класс является контроллером,
 * обрабатывающим HTTP-запросы.
 * @RequestMapping("/rest") - указывает базовый путь URL-адреса для всех конечных точек в этом контроллере. который будет "/rest".
 * @Autowired в Spring Framework используется для автоматического связывания компонентов бина между собой.
 * По умолчанию скоуп бина - синглтон.
 * @GetMapping("managers") используется для сопоставления HTTP-запросов GET с определенными методами в классе контроллера.
 * Он указывает URL-путь для конечной точки и определяет логику обработки запроса GET и генерации ответа.
 * Metod public List<ManagerEntity> getManagerEntitys()  возвращает список всех ManagerEntity в JSON формате
 * @автор rest
 * @версия 1.0
 * @от 2023-11-09
 */

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @GetMapping("managers/all")
    //    http://localhost:8080/rest/managers/all
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();

    }

    @GetMapping("managerStatus/{status}")
    //    http://localhost:8080/rest/managerStatus/ACTIVE
    //    http://localhost:8080/rest/managerStatus/BUSINESS_TRIP
    public List<Manager> getAllManagersByStatus(@PathVariable String status) {
        return managerService.getAllManagersByStatus(ManagerStatus.valueOf(status));
    }

    @GetMapping("manager/{id}")
    //    http://localhost:8080/rest/manager/id?id=1
    //    public Optional<Manager> getManagerById(Integer id) {

    //    http://localhost:8080/rest/manager/5
    public Optional<Manager> getManagerById(@PathVariable Long id) {
        return managerService.getManagerById(id);
    }


    @DeleteMapping ("deleteManager/{id}")
    //    http://localhost:8080/rest/deleteManager/id?id=30
    public void delete(Long id) {
        managerService.deleteById(id);
    }

    @PostMapping("createManager")
    //    http://localhost:8080/rest/deleteManager
    public Manager save(@RequestBody Manager manager) {
        return managerService.create(manager);
    }

    @PostMapping("editManager/{id}")
    //    http://localhost:8080/rest/editManager/5
    public Manager edit(@PathVariable Long id, @RequestBody Manager manager) {
        return managerService.editManager(id, manager);
    }


    @GetMapping("ManagersWorkingWith/{data}")
    //    http://localhost:8080/rest/ManagersWorkingWith/2023-07-14
    public List<Manager> getAllManagersWorkingWith(@PathVariable String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDate.parse(data, formatter).atStartOfDay();
        return managerService.getAllManagersWorkingWith(dateTime);
    }

    @GetMapping("ManagersWorkingWith/{dataWith}/to/{dataTo}")
    //    http://localhost:8080/rest/ManagersWorkingWith/2023-07-14/to/2023-07-16
    //    http://localhost:8080/rest/ManagersWorkingWith/1998-07-08/to/1998-07-20
    public List<Manager> getAllManagersWorkingWith(@PathVariable String dataWith, @PathVariable String dataTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTimeWith = LocalDate.parse(dataWith, formatter).atStartOfDay();
        LocalDateTime dateTimeTo = LocalDate.parse(dataTo, formatter).atStartOfDay();
        return managerService.getAllManagersWorkingWithTo(dateTimeWith, dateTimeTo);
    }
}
