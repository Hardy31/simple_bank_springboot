package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.business.exeption.*;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.data_access.ProductRepository;
import com.bankdone.simple_bank_springboot.dto.AgreementCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AgreementDTO;
import com.bankdone.simple_bank_springboot.dto.AgreementListDTO;
import com.bankdone.simple_bank_springboot.dto.ProductDTO;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.AgreementStatus;
import com.bankdone.simple_bank_springboot.mapper.AgreementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
 * <p>
 * TODO: Изменение параметров допустимо для полей полей проуентная ставка, продукт, счет?
 * TODO: Допустимо ли удаление  соглашенияи и редактирование соглашения ?
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
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final AgreementMapper agreementMapper;


    /**
     * Создает (на основании переданных )и возвращает вновь созданное соглашение
     *
     * @param agreementCreateDTO AgreementCreateDTO
     * @return AgreementDTO
     */

    @CachePut("Agreements")
    public AgreementDTO create(AgreementCreateDTO agreementCreateDTO) {
        if (Double.parseDouble(agreementCreateDTO.getSum()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        if (Double.parseDouble(agreementCreateDTO.getInterestRate()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        String productID = agreementCreateDTO.getProductId();
        Long productId = Long.parseLong(productID);
        String accountID = agreementCreateDTO.getAccountId();
        Long accountId = Long.parseLong(accountID);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND)
        );

        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)
        );

        Agreement toEntity = agreementMapper.createToEntity(agreementCreateDTO);
        toEntity.setProduct(product);
        toEntity.setAccount(account);
        AgreementDTO result = agreementMapper.convertToDTO(agreementRepository.save(toEntity));

        return result;
    }

    /**
     * Возвращает Соглашение по id
     *
     * @param id Long
     * @return Agreement
     */
    @Override
    public AgreementDTO getById(long id) {
        Agreement agreement = agreementRepository.findById(id).orElseThrow(
                () -> new AgreementNotFoundExeption(ErrorMessage.AGREEMENT_NOT_FOUND)
        );
        AgreementDTO result = agreementMapper.convertToDTO(agreement);
        return result;
    }

    /**
     * Редактирование Соглашение. поиск редактируемого поId передданному в теле обекта Соглашение.
     * TODO : Иземнение можно производить если по данному соглашению не было проведено операций.
     *
     * @param agreementDTO
     * @return Agreement - измененное соглашение
     */
    @Override
    @CachePut("Agreements")
    public AgreementDTO edit(AgreementDTO agreementDTO) {

        if (Double.parseDouble(agreementDTO.getSum()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }
        if (Double.parseDouble(agreementDTO.getInterestRate()) < 0.0) {
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }
        Long productId = Long.parseLong(agreementDTO.getProductDTO().getId());
        Long accountId = Long.parseLong(agreementDTO.getAccountDTO().getId());

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND)
        );
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND)
        );

        Agreement agreement = agreementRepository.findById(Long.valueOf(agreementDTO.getId())).orElseThrow(
                () -> new AgreementNotFoundExeption(ErrorMessage.AGREEMENT_NOT_FOUND)
        );

        agreement.setInterestRate(Double.parseDouble(agreementDTO.getInterestRate()));
        agreement.setStatus(AgreementStatus.valueOf(agreementDTO.getStatus()));
        agreement.setSum(Double.parseDouble(agreementDTO.getSum()));
        agreement.setUpdatedAt(LocalDateTime.now());
        agreement.setAccount(account);
        agreement.setProduct(product);

        return agreementMapper.convertToDTO(agreementRepository.save(agreement));
    }

    /**
     * Удаляет аргумент  по id
     * TODO : корректней наверное было бы сделать не удаление а дизактивацию, особенно если были совершины операции по данному соглашению!
     *
     * @param id
     */
    @Override
    @CachePut("Agreements")
    public void delete(Long id) {
        agreementRepository.deleteById(id);
    }

    /**
     * Возвращает список  всех соглашений
     *
     * @return List<Agreement>
     */
    @Override
    @Cacheable("Agreements")
    public List<AgreementDTO> getAll() {

        return new AgreementListDTO(agreementMapper.convertToAgreementDTOList(
                agreementRepository.findAll()
            )
        ).getAgreementDTOLeist();
    }
}
