package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ProductService;
import com.bankdone.simple_bank_springboot.data_access.ProductRepository;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  * Класс  ProductServiseImpl является реализацией интерфейса ProductServise.
 *  * Предоставляет методы для выполнения различных операций, связанных с продуктом.
 *  *
 *  * @Service: эта аннотация используется для указания того, что этот класс является компонентом службы в среде Spring.
 *  * @RequiredArgsConstructor: эта аннотация взята из библиотеки Lombok и генерирует конструктор с обязательными аргументами.
 *  * для последних полей. Это позволяет нам внедрять зависимости с помощью внедрения конструктора (взамен @Autowired).
 *  * @Slf4j: Эта аннотация взята из библиотеки Lombok и генерирует поле для логирования.
 *  * @Transactional: эта аннотация используется в Spring для определения границ транзакций для методов или классов.
 *  * При применении к методу или классу это указывает на то, что для аннотированного метода необходимо создать транзакцию.
 *  * или все методы внутри аннотированного класса.
 *  * Транзакционные границы гарантируют, что группа операций выполняется как одна атомарная единица.
 *  * Важно отметить, что аннотацию @Transactional следует применять к методам, изменяющим данные.
 *  * или выполнить несколько операций с базой данных, чтобы обеспечить целостность и согласованность данных.
 * @автор: alex
 * @от :    05.12.2023
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiseImpl implements ProductService {

    /**
     * * ProductRepository: это поле используется для доступа к данным продукта в базе данных.
     */
    private final ProductRepository productRepository;

    /**
     * Создаетпродукт по переданным параметрам
     * @param product Prodern - данные в обекте
     * @return
     */
    @Override
    @CacheEvict("Products")
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /**
     * Возвращает список всех продуктов
     * @return List<Product>
     */
    @Override
    @Cacheable("Products")
    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Редактирует продукт по переданным параметрам
     * @param id Long для поиска продукиа по id
     * @param product Product - новые значения в теле обекта
     * @return Product - измененный обект
     */
    @Override
    @CacheEvict("Products")
    public Product edit(Long id, Product product) {
        productRepository.save(product);
        Product updateProduct = productRepository.findById(id).get();
        return updateProduct;
    }

    /**
     * Удаляет продукт по id
     * @param id Long
     */
    @Override
    @CacheEvict("Products")
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Возвращает список продуктов  по переданному статусу
     * @param status ЗкщвгсеЫефегы
     * @return List<Product>
     */
    @Override
    public List<Product> getAllByStatus(String status) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        return productRepository.findAllByStatus(productStatus);
    }

    /**
     * Возвращает список продуктов по статусу и выбранному коду валют
     * @param status ProductStatus
     * @param CaCode CurrencyCode
     * @return List<Product>
     */
    @Override
    public List<Product> getAllByStatusAndCurrencyCode(String status, String CaCode) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        CurrencyCode currencyCode = CurrencyCode.valueOf(CaCode);
        return productRepository.findAllByStatusAndCurrencyCode(productStatus, currencyCode);
    }

    /**
     * Возвращает список продуктов по статусу, выбранному коду валют и процентной ставки
     * @param status ProductStatus
     * @param caCode CurrencyCode
     * @param rate Double - процентная ставка
     * @return
     */
    @Override
    public List<Product> getAllByStatusAndCurrencyCodeAndRate(String status, String caCode, Double rate) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        CurrencyCode currencyCode = CurrencyCode.valueOf(caCode);
        return productRepository.findAllByStatusAndCurrencyCodeAnfRate(productStatus, currencyCode, rate);
    }
}
