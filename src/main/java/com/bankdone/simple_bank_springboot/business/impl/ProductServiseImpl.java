package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ProductService;
import com.bankdone.simple_bank_springboot.business.exeption.ErrorMessage;
import com.bankdone.simple_bank_springboot.business.exeption.ManagerNotFoundException;
import com.bankdone.simple_bank_springboot.business.exeption.NegativeDataException;
import com.bankdone.simple_bank_springboot.business.exeption.ProductNotFoundException;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.data_access.ProductRepository;
import com.bankdone.simple_bank_springboot.dto.ProductCreateDTO;
import com.bankdone.simple_bank_springboot.dto.ProductDTO;
import com.bankdone.simple_bank_springboot.dto.ProductListDTO;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import com.bankdone.simple_bank_springboot.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiseImpl implements ProductService {

    /**
     * * ProductRepository: это поле используется для доступа к данным продукта в базе данных.
     */
    private final ProductRepository productRepository;
    private final ManagerRepository managerRepository;
    private final ProductMapper productMapper;

    /**
     * Создаетпродукт по переданным параметрам
     * @param productCreateDTO Prodern - данные в обекте
     * @return
     */
    @Override
    @CachePut("Products")
    public ProductDTO create(ProductCreateDTO productCreateDTO) {
        if(Double.parseDouble(productCreateDTO.getInterestRate()) < 0.0){
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        if(Integer.parseInt(productCreateDTO.getProductLimit()) < 0){
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }
        Product product = productMapper.CreateToEntity(productCreateDTO);
        product.setCreatedAt(LocalDateTime.now());
        Product result = productRepository.save(product);
        log.info("ProductServiseImpl create(ProductCreateDTO productCreateDTO) result {}", result);
        return productMapper.convertToDTO(result);
    }

    /**
     * Возвращает список всех продуктов
     * @return List<Product>
     */
    @Override
    @CacheEvict("Products")
    public List<ProductDTO> getAll() {
        List<ProductDTO> productDTOList = new ProductListDTO(
                productMapper.convertToProductDTOList(
                        productRepository.findAll()
                )
        ).getProductDTOList();

        return productDTOList;
    }

    /**
     * Редактирует продукт по переданным параметрам
     * @param id Long для поиска продукиа по id
     * @param productDTO Product - новые значения в теле обекта
     * @return Product - измененный обект
     */
    @Override
    @CacheEvict("Products")
    public ProductDTO edit(Long id, ProductDTO productDTO) {
        if(Double.parseDouble(productDTO.getInterestRate()) < 0.0){
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        if(Integer.parseInt(productDTO.getProductLimit()) < 0){
            throw new NegativeDataException(ErrorMessage.NEGATIVE_DATA);
        }

        var product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND)
        );

        Long managerId = Long.parseLong(productDTO.getManagerId());
        Manager manager = managerRepository.findById(managerId).orElseThrow(
                () -> new ManagerNotFoundException(ErrorMessage.Manager_NOT_FOUND)
        );

        product.setName(productDTO.getName());
        product.setStatus(ProductStatus.valueOf(productDTO.getStatus()));
        product.setCurrencyCode(CurrencyCode.valueOf(productDTO.getCurrencyCode()));
        product.setInterestRate(Double.parseDouble(productDTO.getInterestRate()));
        product.setProductLimit(Integer.parseInt(productDTO.getProductLimit()));
        product.setUpdatedAt(LocalDateTime.now());
        product.setManager(manager);

        Product result = productRepository.save(product);
        return productMapper.convertToDTO(result);
    }

    /**
     * Удаляет продукт по id
     * @param id Long
     */
    @Override
    @CachePut("Products")
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Возвращает список продуктов  по переданному статусу
     * @param status ЗкщвгсеЫефегы
     * @return List<Product>
     */
    @Override
    public List<ProductDTO> getAllByStatus(String status) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        List<ProductDTO> productListDTO = new ProductListDTO(
                productMapper.convertToProductDTOList(
                        productRepository.findAllByStatus(productStatus)
                )
        ).getProductDTOList();
        return productListDTO;
    }

    /**
     * Возвращает список продуктов по статусу и выбранному коду валют
     * @param status ProductStatus
     * @param curCode CurrencyCode
     * @return List<Product>
     */
    @Override
    public List<ProductDTO> getAllByStatusAndCurrencyCode(String status, String curCode) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        CurrencyCode currencyCode = CurrencyCode.valueOf(curCode);
        List<ProductDTO> productListDTO = new ProductListDTO(
                productMapper.convertToProductDTOList(
                        productRepository.findAllByStatusAndCurrencyCode(productStatus, currencyCode)
                )
        ).getProductDTOList();
        return productListDTO;
    }

    /**
     * Возвращает список продуктов по статусу, выбранному коду валют и процентной ставки
     * @param status ProductStatus
     * @param curCode CurrencyCode
     * @param rate Double - процентная ставка
     * @return
     */
    @Override
    public List<ProductDTO> getAllByStatusAndCurrencyCodeAndRate(String status, String curCode, Double rate) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        CurrencyCode currencyCode = CurrencyCode.valueOf(curCode);

        List<ProductDTO> productListDTO = new ProductListDTO(
                productMapper.convertToProductDTOList(
                        productRepository.findAllByStatusAndCurrencyCodeAndRate(productStatus, currencyCode, rate)
                )
        ).getProductDTOList();
        return productListDTO;
    }
}
