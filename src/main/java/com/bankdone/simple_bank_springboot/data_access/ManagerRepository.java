package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Аннотация @Repository не нужна так как интерфейс наследуется от JpaRepository<Manager, Integer>!
// где Manager это entity с которым будет работать данный репозиторий и Integer это PrimaryKey нащей таблици Long!
//@Repository
public interface ManagerRepository extends  JpaRepository<Manager, Long> {

    List<Manager> findAllByStatus(ManagerStatus status);

    List<Manager> findAllByCreatedAtAfter(LocalDateTime dateTime);

    List<Manager> findAllByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );

    Manager findByFirstNameAndLastName(String firstName, String lastName);



}
