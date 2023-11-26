package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.ProductService;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("createProduct")
    //    http://localhost:8080/rest/createProduct
    public Product create(@RequestBody Product product){
        return  productService.create(product);
    }

    @PutMapping("editProduct/{id}")
    //    http://localhost:8080/rest/editProduct/7
    public Product edit(@PathVariable Long id,@RequestBody Product product){
        return  productService.edit(id, product);
    }

    @GetMapping("product/all")
    //    http://localhost:8080/rest/product/all
    public List<Product> getAll() {
        return productService.getAll();
    }

    @DeleteMapping("deleteProduct/{id}")
    //    http://localhost:8080/rest/deleteProduct/8
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @GetMapping("productStatus/{status}")
    //    http://localhost:8080/rest/productStatus/ACTIVE
    //    http://localhost:8080/rest/productStatus/PILOT
    public List<Product> getAllByStatus(@PathVariable String status){
        return productService.getAllByStatus(status);
    }

    @GetMapping("productStatus/{status}/currencyCode/{code}")
    //    http://localhost:8080/rest/productStatus/PILOT/currencyCode/RUB
    public List<Product> getAllByStatusAndCarrencyCode(@PathVariable String status,@PathVariable String code){
        return productService.getAllByStatusAndCarrencyCode(status, code);
    }

    @GetMapping("productStatus/{status}/currencyCode/{code}/interestRate/{rate}")
    //    http://localhost:8080/rest/productStatus/ACTIVE/currencyCode/RUB/interestRate/10.2500
    public List<Product> getAllByStatusAndCarrencyCodeAndRate(@PathVariable String status,@PathVariable String code,@PathVariable Double rate){
        return productService.getAllByStatusAndCarrencyCodeAndRate(status, code, rate);
    }
}
