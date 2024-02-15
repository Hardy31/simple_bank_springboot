package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.business.exeption.AccountNotFoundException;
import com.bankdone.simple_bank_springboot.business.exeption.ErrorMessage;
import com.bankdone.simple_bank_springboot.business.exeption.NegativeDataException;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.dto.AccountCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AccountDTO;
import com.bankdone.simple_bank_springboot.dto.AccountListDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import com.bankdone.simple_bank_springboot.entity.enums.AccountStatus;
import com.bankdone.simple_bank_springboot.entity.enums.AccountType;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final AccountMapper accountMapper;

    /**
     * согдание счета по переданным параментам
     * @param accountCreateDTO AccountCreateDTO
     * @return Account с присвоенным id
     */
    @CacheEvict("Accounts")
    public AccountDTO create(AccountCreateDTO accountCreateDTO) {
        if (accountCreateDTO.getClientId() == null) {
            throw new NullPointerException("clientId cannot be null");
        }

        if (Double.parseDouble(accountCreateDTO.getBalance()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        Account account = accountRepository.save(accountMapper.creatToEntity(accountCreateDTO));
        AccountDTO result = accountMapper.convertToDTO(accountRepository.save(account));

        return result;
    }

    /**
     * Возвращает счет по id
     * @param id long
     * @return AccountDTO
     */
    public AccountDTO getById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(
                ()->new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)
        );
        AccountDTO result = accountMapper.convertToDTO(account);
        return result;

    }
    /**
     * Возвращает список  всех счетов.
     * @return List<AccountDTO>
     */
    @Override
    @Cacheable("Accounts")
    public AccountListDTO getAll() {
        List<Account> accountList = accountRepository.findAll();
        accountMapper.convertToAccountDTOList(accountList);
        return new AccountListDTO(accountMapper.convertToAccountDTOList(accountList));
    }





    /**
     * редактирование  счета по переданным параментам
     * TODO: могут ли быть изменения по счету для полей Имя, тип, код валюты?,
     * @param accountDTO AccountDTO
     * @return AccountDTO с присвоенным id
     */
    @CacheEvict("Accounts")
    public AccountDTO edit(AccountDTO accountDTO) {
        if (Double.parseDouble(accountDTO.getBalance()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        Account account = accountRepository.findById(Long.valueOf(accountDTO.getId())).orElseThrow(
                () -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)
        );

        account.setName(accountDTO.getName());
        account.setType(AccountType.valueOf(accountDTO.getType()));
        account.setStatus(AccountStatus.valueOf(accountDTO.getStatus()));
        account.setBalance(Double.parseDouble(accountDTO.getBalance()));
        account.setCode(CurrencyCode.valueOf(accountDTO.getCurrencyCode()));
        account.setUpdatedAt(LocalDateTime.now());
        account.setClient(account.getClient());


        return accountMapper.convertToDTO(accountRepository.save(account));
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
