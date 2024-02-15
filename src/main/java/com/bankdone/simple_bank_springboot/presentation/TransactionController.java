package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.TransactionService;
import com.bankdone.simple_bank_springboot.dto.TransactionCreateDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionDTO;
import com.bankdone.simple_bank_springboot.dto.TransactionListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * @param  transactionCreateDTO
     * @return TransactionDTO
     */
    @PostMapping("")
    public TransactionDTO create(@RequestBody TransactionCreateDTO transactionCreateDTO){
        log.info("TransactionService - create(@RequestBody CreateTransactionDTO createTransactionDTO) ");
        return  transactionService.createTransaction(transactionCreateDTO);

    }

    /**
     * Возвращает список  всех транзакций
     * @return TransactionListDTO
     */
    @GetMapping("")
    public TransactionListDTO getAll(){
        log.info("TransactionService - getAll() ");
        return transactionService.getAllTransaction();
    }


    /**
     * Возвращает транзакцию по Id
     * @param  id
     * @return TransactionDTO
     */
    @GetMapping("/{id}")
    public TransactionDTO getById(@PathVariable Long id){
        log.info("TransactionService - getById(@PathVariable Long id) : id = {} ", id);
        return  transactionService.getTransactionById(id);
    }
}
