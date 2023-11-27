package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.ProductRepository;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product edit(Long id, Product product) {
        productRepository.save(product);
//        Long productId = product.getId();
        Product updateProduct = productRepository.findById(id).get();
        return updateProduct;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllByStatus(String status) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        return productRepository.findAllByStatus(productStatus);
    }

    public List<Product> getAllByStatusAndCarrencyCode(String status, String CaCode) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        CurrencyCode currencyCode = CurrencyCode.valueOf(CaCode);
        return productRepository.findAllByStatusAndCurrencyCode(productStatus, currencyCode);
    }

    public List<Product> getAllByStatusAndCarrencyCodeAndRate(String status, String caCode, Double rate) {
        ProductStatus productStatus = ProductStatus.valueOf(status);
        CurrencyCode currencyCode = CurrencyCode.valueOf(caCode);
        return productRepository.findAllByStatusAndCurrencyCodeAnfRate(productStatus, currencyCode, rate);
    }

}