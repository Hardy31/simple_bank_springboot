package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.dto.AccountCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AccountDTO;
import com.bankdone.simple_bank_springboot.dto.AccountListDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>Класс  AccountController</h3><br>
 * <p>
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Account.<br>
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
@RequiredArgsConstructor
@RequestMapping("/rest/accounts")
public class AccountsController {

    /**
     * объект AccountService для получения результата запроса из сервис слоя
     */
    private final AccountService accountService;

    /**
     * При отправке Get запроса на  URN rest/accounts
     * возвращает список всех клиентов
     */
    @GetMapping("")
    public AccountListDTO getAll() {
        log.info("AccountsController getAll()");
        return accountService.getAll();
    }

    /**
     * При отправке Get запроса на  URN rest/accounts/{id}
     * возвращает список всех клиентов
     */
    @GetMapping("/{id}")
    public AccountDTO getById(@PathVariable Long id) {
        log.info("AccountsController getById(@PathVariable Long id) : {}", id);
        return accountService.getById(id);
    }

    /**
     * При отправке Post запроса на  URN rest/accounts
     *  сохраняет в БД и возвращает созданный Счет
     */
    @PostMapping("")
    public AccountDTO cteate(@RequestBody AccountCreateDTO accountCreateDTO) {
        log.info("AccountsController cteate(@RequestBody Account account) : {}", accountCreateDTO);
        return accountService.create(accountCreateDTO);
    }

    /**
     * При отправке Put запроса на  URN rest/accounts
     *  редактирует  в БД и возвращает измененный Счет
     */
    @PutMapping("")
    public AccountDTO editAccount(@RequestBody AccountDTO accountDTO) {
        log.info("AccountsController editAccount(@RequestBody Account account) : {}", accountDTO);
        return accountService.edit(accountDTO);
    }

    /**
     * При отправке Delite запроса на  URN rest/accounts/{id}
     *  удаляет их БД Счет по id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("AccountsController delete(@PathVariable Long id) : {}", id);
        accountService.delite(id);
    }

}
