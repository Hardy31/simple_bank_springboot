package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс  AgreementRepository наследуется от JpaRepository<Client, Integer>!
 * поэтому Аннотация @Repository не используется (не обязательна).
 *
 * Optional<Account> findByID(@Param("id") Long id); запрос сформирован вручную.
 * Поиск счета по Id
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-12-04
 */
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
}
