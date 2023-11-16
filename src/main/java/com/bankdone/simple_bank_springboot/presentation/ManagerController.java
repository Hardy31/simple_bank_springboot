package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
//@RequiredArgsConstructor
public class ManagerController {

    @Autowired
    private ManagerService managerService;

//    http://localhost:8080/rest/managers/all
    @GetMapping("managers/all")
//    @ResponseStatus(HttpStatus.OK)
    public List<Manager> getAllManagers() {
//        return managerService.listAll().stream().map(Manager::toManager).collect(Collectors.toList());
        return managerService.listAll();

    }

    @GetMapping("managerStatus/{status}")
    //    http://localhost:8080/rest/manager/status?status=ACTIVE
    //    http://localhost:8080/rest/manager/status?status=BUSINESS_TRIP
//    public List<Manager> getAllManagersByStatus(String status) {


        //    http://localhost:8080/rest/managerStatus/ACTIVE
        //    http://localhost:8080/rest/manager/BUSINESS_TRIP
        public List<Manager> getAllManagersByStatus(@PathVariable String status) {

//        ManagerStatus.valueOf(status)
        System.out.println("stringStatus Controller " + status);
        return managerService.getAllManagersByStatus(ManagerStatus.valueOf(status));
    }


//    @GetMapping("manager/{id:[^\\d]}")
    @GetMapping("manager/{id}")
//    http://localhost:8080/rest/manager/id?id=1
//    public Optional<Manager> getManagerById(Integer id) {

//    http://localhost:8080/rest/manager/5
    public Optional<Manager> getManagerById(@PathVariable Integer id) {
        return managerService.getManagerById(id);
    }


////    http://localhost:8080/rest/deleteManager/id?id=30
//    @DeleteMapping("deleteManager/{id}")
    @GetMapping("deleteManager/{id}")
    public void delete(Integer id) {
        managerService.deleteById(id);
    }
}
