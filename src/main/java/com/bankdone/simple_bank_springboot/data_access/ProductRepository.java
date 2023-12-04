package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Класс  ProductRepository наследуется от CrudRepository <Product, Long>!
 * поэтому Аннотация @Repository не используется (не обязательна).
 *
 * List<Product> findAllByStatus(@Param("status") ProductStatus status);
 * запрос сформирован вручную. Поиск всех продуктов  по статусу
 *
 * List<Product> findAllByStatusAndCurrencyCode(
 *         @Param("status") ProductStatus status,
 *         @Param("code")CurrencyCode currencyCode
 * ) ;
 * запрос сформирован вручную. Поиск всех продуктов  по статусу и коду валюты
 *
 * List<Product> findAllByStatusAndCurrencyCodeAnfRate(
 *         @Param("status") ProductStatus status,
 *         @Param("code")CurrencyCode currencyCode,
 *         @Param("rate")double interestRate
 * ) ;
 * запрос сформирован вручную. Поиск всех продуктов при совподении статуса, коду валюты и процентной ставки
 *
 *
 * Все остальные методы используют автоматическую генерацию запроса по имени
 *
 * @автор  Hardy
 * @версия 1.0
 * @от   2023-12-04
 */

public interface ProductRepository extends CrudRepository <Product, Long>{

    @Query("SELECT p FROM Product  AS p WHERE p.status = :status")
    List<Product> findAllByStatus(@Param("status") ProductStatus status);

    @Query("SELECT p FROM Product  AS p WHERE p.status = :status AND p.currencyCode = :code")
    List<Product> findAllByStatusAndCurrencyCode(
            @Param("status") ProductStatus status,
            @Param("code")CurrencyCode currencyCode
    ) ;

    @Query("SELECT p FROM Product  AS p WHERE p.status = :status AND p.currencyCode = :code AND p.interestRate = :rate")
    List<Product> findAllByStatusAndCurrencyCodeAnfRate(
            @Param("status") ProductStatus status,
            @Param("code")CurrencyCode currencyCode,
            @Param("rate")double interestRate
    ) ;

}
