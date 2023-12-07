package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.TransactionService;
import com.bankdone.simple_bank_springboot.dto.CreateTransactionDTO;
import com.bankdone.simple_bank_springboot.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>Класс  TransactionController</h3><br>
 * <p>
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Transaction.<br>
 * <p>
 * - Аннотация RestController (@Controller + @ ResponsBody) указывает, что этот класс является REST контроллером, <br>
 * = Аннотация RequestMapping("/rest/transaction") - указывает базовый путь URL-адреса для всех конечных точек
 *
 * @автор: alex
 * @от :    05.12.2023
 */

@Slf4j
@RestController
@RequestMapping("/rest/transactions")
@RequiredArgsConstructor
public class TransactionController {
//    CRUD
    /**
     * объект TransactionService для получения результата запроса из сервис слоя
     */
    private final TransactionService transactionService;


    /**
     * Данный метод вохвращает  сохраненную в БД транзакцию, если коды валют
     * софподают и достаточно средст для перевода.
     * ! Не использовать если осуществляется снятие или пополнение счета
     * TODO: для снятия и пополнения средст необходимы другие  методы!
     * @param  createTransactionDTO
     * @return Transaction
     */
    @PostMapping("")
    public Transaction create(@RequestBody CreateTransactionDTO createTransactionDTO){
        log.info("TransactionService - create(@RequestBody CreateTransactionDTO createTransactionDTO) ");
        return  transactionService.createTransaction(createTransactionDTO);

    }

    /**
     * Возвращает список  всех транзакций
     * @return List<Transaction>
     */
    @GetMapping("")
    public List<Transaction> getAll(){
        log.info("TransactionService - getAll() ");
        return transactionService.getAllTransaction();
    }


    /**
     * Возвращает транзакцию по Id
     * @param  id
     * @return Transaction
     */
    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id){
        log.info("TransactionService - getById(@PathVariable Long id) : id = {} ", id);
        return  transactionService.getTransactionById(id).get();
    }
}
