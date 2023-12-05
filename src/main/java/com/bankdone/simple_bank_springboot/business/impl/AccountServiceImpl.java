package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс  AccountServiceImpl является реализацией интерфейса AccountService.
 *  Предоставляет методы для выполнения различных операций, связанных со счетом.
 *
 *  @Service: эта аннотация используется для указания того, что этот класс является компонентом службы в среде Spring.
 *  @RequiredArgsConstructor: эта аннотация взята из библиотеки Lombok и генерирует конструктор с обязательными аргументами.
 *  для последних полей. Это позволяет нам внедрять зависимости с помощью внедрения конструктора (взамен @Autowired).
 *  @Slf4j: Эта аннотация взята из библиотеки Lombok и генерирует поле для логирования.
 *  @Transactional: эта аннотация используется в Spring для определения границ транзакций для методов или классов.
 *  При применении к методу или классу это указывает на то, что для аннотированного метода необходимо создать транзакцию.
 *  или все методы внутри аннотированного класса.
 *  Транзакционные границы гарантируют, что группа операций выполняется как одна атомарная единица.
 *  Важно отметить, что аннотацию @Transactional следует применять к методам, изменяющим данные.
 *  или выполнить несколько операций с базой данных, чтобы обеспечить целостность и согласованность данных.
 *
 * TODO: Логирование по движению по счетам должно ли писаться в отдельный файл?
 * TODO: Изменение параметров допустимо для полей полей Имя, тип, код валюты
 * TODO: Допустимо ли удаление  счета ? Или перевод статуса на Неактивен без удаление из БД?
 *
 * @пакет: simple_bank_springboot
 * @автор: alex
 * @от :    05.12.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    /**
     * AccountRepository используется для получения данных по счетам из БД
     */
    private final AccountRepository accountRepository;

    /**
     * Возвращает список  всех счетов.
     * @return List<Account>
     */
    @Override
    @Cacheable("Accounts")
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }

    /**
     * Возвращает счет по id
     * @param id long
     * @return Account
     */
    public Account getById(Long id) {
        return accountRepository.findById(id).get();
    }

    /**
     * согдание счета по переданным параментам
     * @param account Account
     * @return Account с присвоенным id
     */
    @CacheEvict("Accounts")
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    /**
     * редактирование  счета по переданным параментам
     * TODO: могут ли быть изменения по счету для полей Имя, тип, код валюты?,
     * @param account Account
     * @return Account с присвоенным id
     */
    @CacheEvict("Accounts")
    public Account edit(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Удаление счета по id
     * TODO: Удаление не должно быть!. Должен меняться статус.
     * @param id Long
     */
    @CacheEvict("Accounts")
    public void delite(Long id) {
        accountRepository.deleteById(id);
    }
}
