package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.entity.Product;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    List<Product> getAll();

    Product edit(Long id, Product product);

    void delete(Long id);

    List<Product> getAllByStatus(String status);

    List<Product> getAllByStatusAndCurrencyCode(String status, String CaCode);

    List<Product> getAllByStatusAndCurrencyCodeAndRate(String status, String caCode, Double rate);


}
