package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ProductService;
import com.bankdone.simple_bank_springboot.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>Класс  ProductController</h3><br>
 * <p>
 * Данный клас является обработчиком запросов которые будут поступать от dispatcher Servlet для Entity Product.<br>
 * <p>
 * - Аннотация RestController (@Controller + @ ResponsBody) указывает, что этот класс является REST контроллером, <br>
 * = Аннотация RequestMapping("/rest") - указывает базовый путь URL-адреса для всех конечных точек
 *
 * @автор Hardy
 * @версия 1.0
 * @от 2023-11-09
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/products")
public class ProductController {

    /**
     * объект ProductService для получения результата запроса из сервис слоя
     */
    private final ProductService productService;

    /**
     * При отправке Post запроса на  URN rest/products
     * возвращает объект Product <br>
     * http://localhost:8080/rest/products
     */
    @PostMapping("")
    public Product create(@RequestBody Product product) {
        log.info("ProductController create(@RequestBody Product product) {}", product);
        return productService.create(product);
    }

    /**
     * При отправке Put запроса на  URN rest/products/{id}
     * возвращает измененный объект Product <br>
     * http://localhost:8080/rest/products/7
     */
    @PutMapping("/{id}")
    public Product edit(@PathVariable Long id, @RequestBody Product product) {
        log.info("ProductController edit(@PathVariable Long id, @RequestBody Product product)" +
                " id = {}, product = {}", id, product);
        return productService.edit(id, product);
    }

    /**
     * При отправке Get запроса на  URN rest/products
     * возвращает список всех объектов Product <br>
     * http://localhost:8080/rest/products
     */
    @GetMapping("")
    public List<Product> getAll() {
        log.info("ProductController getAll()");
        return productService.getAll();
    }


    /**
     * При отправке Delete запроса на  URN rest/products/{id}
     * удаляет из БД объект Product по его id <br>
     * http://localhost:8080/rest/products/8
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("ProductController delete(@PathVariable Long id) id = {}", id);
        productService.delete(id);
    }

    /**
     * При отправке Get запроса на  URN rest/products/by-status/{status}
     * возвращает список всех объектов Product с переданным статусом <br>
     * http://localhost:8080/rest/products/by-status/ACTIVE <br>
     * http://localhost:8080/rest/products/by-status/PILOT
     */
    @GetMapping("/by-status/{status}")
    public List<Product> getAllByStatus(@PathVariable String status) {
        log.info("ProductController etAllByStatus(@PathVariable String status) status = {}", status);
        return productService.getAllByStatus(status);
    }


    /**
     * При отправке Get запроса на  URN rest/products/by-status/{status}/and-code/{code}
     * возвращает список всех объектов Product с переданным кодом <br>
     * http://localhost:8080/rest/products/by-status/PILOT/and-code/RUB
     */
    @GetMapping("by-status/{status}/and-code/{code}")
    public List<Product> getAllByStatusAndCarrencyCode(
            @PathVariable String status,
            @PathVariable String code
    ) {
        log.info("ProductController getAllByStatusAndCarrencyCode(" +
                "@PathVariable String status = {}, @PathVariable String code = {})" , status, code);
        return productService.getAllByStatusAndCurrencyCode(status, code);
    }

    /**
     * При отправке Get запроса на  URN rest/products/status/{status}/code/{code}/interest-rate/{rate}
     * возвращает список всех объектов Product с переданнымми  парамаетрами <br>
     * http://localhost:8080/rest/products/status/ACTIVE/code/RUB/interest-rate/10.2500
     */
    @GetMapping("/status/{status}/code/{code}/interest-rate/{rate}")
    public List<Product> getAllByStatusAndCarrencyCodeAndRate(
            @PathVariable String status,
            @PathVariable String code,
            @PathVariable Double rate
    ) {
        log.info("ProductController getAllByStatusAndCarrencyCode(" +
                "@PathVariable String status = {}, " +
                "@PathVariable String code = {}), " +
                "@PathVariable Double rate = {}" ,
                status, code, rate);
        return productService.getAllByStatusAndCurrencyCodeAndRate(status, code, rate);
    }
}
