package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * Класс  ClientRepository наследуется от JpaRepository<Client, Integer>!
 * поэтому Аннотация @Repository не используется (не обязательна).
 *
 * List<Client> findAllByManager_Id(Long id); использован построитель запросов по имени метода.
 * Поиск клиента по Id
 *
 * Optional<Client> findClientByPhone(String phone); использован построитель запросов по имени метода.
 * Поиск клиента по телефону
 *
 *   List<Client> findClientByAddress(String address); использован построитель запросов по имени метода.
 * Поиск клиентов по адресу
 *
 * List<Client> findClientByStatus(ClientStatus status); использован построитель запросов по имени метода.
 *  * Поиск клиентов по статусуDa
 *
 *
 * List<Client> findClientByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );
 * использован построитель запросов по имени метода.
 * Возвращает список клиентов которые были созданы в промежутке переданных дат ( с по).
 *
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-11-19
 */


@Repository
public interface ClientRepository extends CrudRepository<Client, Long>  {

    List<Client> findAllByManager_Id(Long id);


    Optional<Client> findClientByPhone(String phone);


    List<Client> findClientByAddress(String address);

    List<Client> findClientByStatus(ClientStatus status);

    List<Client> findClientByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );

}
