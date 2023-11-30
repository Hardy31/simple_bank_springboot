package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ProductService;
import com.bankdone.simple_bank_springboot.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>Класс  ProductController</h3><br>
 *
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Product.<br>
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
public class ProductController {

    /**
     * объект ProductService для получения результата запроса из сервис слоя
     */
    private final ProductService productService;

    /**
     * При отправке Post запроса на  URN rest/createProduct
     * возвращает объект Product <br>
     * http://localhost:8080/rest/createProduct
     */
    @PostMapping("createProduct")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    /**
     * При отправке Put запроса на  URN rest/editProduct/{id}
     * возвращает измененный объект Product <br>
     * http://localhost:8080/rest/editProduct/7
     */
    @PutMapping("editProduct/{id}")
    public Product edit(@PathVariable Long id, @RequestBody Product product) {
        return productService.edit(id, product);
    }

    /**
     * При отправке Get запроса на  URN rest/product/all
     * возвращает список всех объектов Product <br>
     * http://localhost:8080/rest/product/all
     */
    @GetMapping("product/all")
    public List<Product> getAll() {
        return productService.getAll();
    }


    /**
     * При отправке Delete запроса на  URN rest/deleteProduct/{id}
     * удаляет из БД объект Product по его id <br>
     * http://localhost:8080/rest/deleteProduct/8
     */
    @DeleteMapping("deleteProduct/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    /**
     * При отправке Get запроса на  URN rest/productStatus/{status}
     * возвращает список всех объектов Product с переданным статусом <br>
     * http://localhost:8080/rest/productStatus/ACTIVE <br>
     * http://localhost:8080/rest/productStatus/PILOT
     */
    @GetMapping("productStatus/{status}")
    public List<Product> getAllByStatus(@PathVariable String status) {
        return productService.getAllByStatus(status);
    }


    /**
     * При отправке Get запроса на  URN rest/currencyCode/{code}
     * возвращает список всех объектов Product с переданным кодом <br>
     * http://localhost:8080/rest/productStatus/PILOT/currencyCode/RUB
     */
    @GetMapping("productStatus/{status}/currencyCode/{code}")
    public List<Product> getAllByStatusAndCarrencyCode(@PathVariable String status, @PathVariable String code) {
        return productService.getAllByStatusAndCurrencyCode(status, code);
    }

    /**
     * При отправке Get запроса на  URN rest/productStatus/{status}/currencyCode/{code}/interestRate/{rate}
     * возвращает список всех объектов Product с переданнымми  парамаетрами <br>
       * http://localhost:8080/rest/productStatus/ACTIVE/currencyCode/RUB/interestRate/10.2500
     */
    @GetMapping("productStatus/{status}/currencyCode/{code}/interestRate/{rate}")
    public List<Product> getAllByStatusAndCarrencyCodeAndRate(@PathVariable String status, @PathVariable String code, @PathVariable Double rate) {
        return productService.getAllByStatusAndCurrencyCodeAndRate(status, code, rate);
    }
}
