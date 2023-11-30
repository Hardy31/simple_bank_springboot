package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//Аннотация @Repository не нужна так как интерфейс наследуется от JpaRepository<Manager, Integer>!
// где Manager это entity с которым будет работать данный репозиторий и Integer это PrimaryKey нашей таблици Long!
//@Repository

/**
 * Класс  ManagerRepository наследуется от JpaRepository<Manager, Integer>!
 * поэтому Аннотация @Repository не нужна.
 * <br/>
 * List<Manager> findAllByStatus(ManagerStatus status); использован построитель запросов по имени метода.
 * Возвращает список менеджеров у которых статус соответствует переданномку в метод статусу.
 *
 * List<Manager> findAllByCreatedAtAfter(LocalDateTime dateTime);использован построитель запросов по имени метода.
 * Возвращает список менеджеров которые были созданы после переданной даты.
 *
 * List<Manager> findAllByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );
 * использован построитель запросов по имени метода.
 * Возвращает список менеджеров которые были созданы в промежутке переданных дат ( с по).
 *
 * Manager findByFirstNameAndLastName(String firstName, String lastName);
 * использован построитель запросов по имени метода.
 * Поиск менеджера по имени и фамилии.
 *
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-11-19
 */


public interface ManagerRepository extends  JpaRepository<Manager, Long> {

    List<Manager> findAllByStatus(ManagerStatus status);

    List<Manager> findAllByCreatedAtAfter(LocalDateTime dateTime);

    List<Manager> findAllByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );

    Manager findByFirstNameAndLastName(String firstName, String lastName);

}
