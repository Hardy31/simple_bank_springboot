package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс  AgrimentServiceImpl является реализацией интерфейса AgrimentService.
 * Предоставляет методы для выполнения различных операций, связанных с аргумент.
 *
 * @Service: эта аннотация используется для указания того, что этот класс является компонентом службы в среде Spring.
 * @RequiredArgsConstructor: эта аннотация взята из библиотеки Lombok и генерирует конструктор с обязательными аргументами.
 * для последних полей. Это позволяет нам внедрять зависимости с помощью внедрения конструктора (взамен @Autowired).
 * @Slf4j: Эта аннотация взята из библиотеки Lombok и генерирует поле для логирования.
 * @Transactional: эта аннотация используется в Spring для определения границ транзакций для методов или классов.
 * При применении к методу или классу это указывает на то, что для аннотированного метода необходимо создать транзакцию.
 * или все методы внутри аннотированного класса.
 * Транзакционные границы гарантируют, что группа операций выполняется как одна атомарная единица.
 * Важно отметить, что аннотацию @Transactional следует применять к методам, изменяющим данные.
 * или выполнить несколько операций с базой данных, чтобы обеспечить целостность и согласованность данных.
 *
 * TODO: Изменение параметров допустимо для полей полей проуентная ставка, продукт, счет?
 * TODO: Допустимо ли удаление  соглашенияи и редактирование соглашения ?
 *
 * @автор: alex
 * @от :    05.12.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    /**
     * AgreementRepository используется для получения доступа к данным  Соглашений
     */
    private final AgreementRepository agreementRepository;

    /**
     * Возвращает Соглашение по id
     * @param id Long
     * @return Agreement
     */
    @Override
    public Agreement getById(long id) {
        return agreementRepository.findById(id).get();
    }

    /**
     * Возвращает список  всех соглашений
     * @return List<Agreement>
     */
    @Override
    @Cacheable("Agreements")
    public List<Agreement> getAll() {
        return (List<Agreement>) agreementRepository.findAll();
    }

    /**
     * Создает (на основании переданных )и возвращает вновь созданное соглашение
     * @param agreement Agreement
     * @return Agreement
     */
    @Override
    @CacheEvict("Agreements")
    public Agreement create(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    /**
     * Удаляет аргумент  по id
     * TODO : корректней наверное было бы сделать не удаление а дизактивацию, особенно если были совершины операции по данному соглашению!
     * @param id
     */
    @Override
    @CacheEvict("Agreements")
    public void delete(Long id) {
        agreementRepository.deleteById(id);
    }

    /**
     * Редактирование Соглашение. поиск редактируемого поId передданному в теле обекта Соглашение.
     * TODO : Иземнение можно производить если по данному соглашению не было проведено операций.
     * @param agreement
     * @return Agreement - измененное соглашение
     */
    @Override
    @CacheEvict("Agreements")
    public Agreement edit(Agreement agreement) {
        return agreementRepository.save(agreement);
    }
}
