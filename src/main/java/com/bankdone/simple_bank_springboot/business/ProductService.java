package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.dto.ProductCreateDTO;
import com.bankdone.simple_bank_springboot.dto.ProductDTO;
import com.bankdone.simple_bank_springboot.entity.Product;
import java.util.List;

public interface ProductService {

    ProductDTO create(ProductCreateDTO productCreateDTO);

    List<ProductDTO> getAll();

    ProductDTO edit(Long id, ProductDTO productDTO);

    void delete(Long id);

    List<ProductDTO> getAllByStatus(String status);

    List<ProductDTO> getAllByStatusAndCurrencyCode(String status, String CaCode);

    List<ProductDTO> getAllByStatusAndCurrencyCodeAndRate(String status, String caCode, Double rate);


}
