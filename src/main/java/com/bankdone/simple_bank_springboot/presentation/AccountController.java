package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

/**
 * <h3>Класс  AccountController</h3><br>
 *
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Account.<br>
 *
 *  - Аннотация RestController (@Controller + @ ResponsBody) указывает, что этот класс является REST контроллером, <br>
 *  = Аннотация RequestMapping("/rest") - указывает базовый путь URL-адреса для всех конечных точек
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class AccountController {

    /**
     * объект AccountService для получения результата запроса из сервис слоя
     */
    private final AccountService accountService;

    /**
     * При отправке Get запроса на  URN rest/account/{id}
     * возвращает список всех клиентов
     */
    @GetMapping("account/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getBiID(id);
    }

    /**
     * При отправке Get запроса на  URN rest/accounts/all
     * возвращает список всех клиентов
     */
    @GetMapping("accounts/all")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    /**
     * При отправке Post запроса на  URN rest/creatAccounts
     *  сохраняет в БД и возвращает созданный Счет
     */
    @PostMapping("creatAccounts")
    public Account cteate(@RequestBody Account account) {
        return accountService.create(account);
    }

    /**
     * При отправке Put запроса на  URN rest/creatAccounts
     *  редактирует  в БД и возвращает измененный Счет
     */
    @PutMapping("editAccount")
    public Account editAccount(@RequestBody Account account) {
        return accountService.edit(account);
    }

    /**
     * При отправке Delite запроса на  URN rest/deleteAccount/{id}
     *  удаляет их БД Счет по id
     */
    @DeleteMapping("deleteAccount/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delite(id);
    }
}
