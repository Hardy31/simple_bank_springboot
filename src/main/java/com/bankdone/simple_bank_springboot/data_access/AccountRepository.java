package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Класс  AccountRepository наследуется от JpaRepository<Client, Integer>!
 * поэтому Аннотация @Repository не используется (не обязательна).
 *
 * Optional<Account> findByID(@Param("id") Long id); запрос сформирован вручную.
 * Поиск счета по Id
 *
 * Все остальные методы используют автоматическую генерацию запроса по имени
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-12-04
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account  AS a WHERE a.id = :id")
    public Optional<Account> findByID(@Param("id") Long id);
}
