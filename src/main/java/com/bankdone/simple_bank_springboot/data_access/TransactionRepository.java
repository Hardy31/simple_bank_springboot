package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Класс  TransactionRepository наследуется от JpaRepository<Transaction, Long> !
 * поэтому Аннотация @Repository не используется (не обязательна).
 * <br/>
 *  использован построитель запросов по имени метода.
 *
 * @автор: alex
 * @от :    07.12.2023
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
