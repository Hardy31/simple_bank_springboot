package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ProductService;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.data_access.ProductRepository;
import com.bankdone.simple_bank_springboot.dto.ProductDTO;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import com.bankdone.simple_bank_springboot.mapper.ProductMapper;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for ProductServiseImplTest")
@Slf4j
class ProductServiseImplTest {
    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private ProductRepository productRepository;
    private Manager managerTemplate;
    private Product productTemplate;
    private List<Product> productTemplatetList;
    private ProductService productService;
    private ProductMapper productMapper;


    @BeforeEach
    void setUp(){
        managerTemplate = CreatorFakeEntity.getFakeManager(2L);
        productTemplate = CreatorFakeEntity.getFakeProduct(3L);
        productTemplatetList = new ArrayList<>(List.of(productTemplate));
        productService = new ProductServiseImpl(productRepository, managerRepository, productMapper );
    }

    @Test
    void create() {
        Product productAssert = CreatorFakeEntity.creatFakeProduct();
        log.info(" test {}", productAssert);
        when(productRepository.save(any())).thenReturn(productTemplate);
//        ProductDTO resultPtoductDTO = productService.create(productAssert);
//        verify(productRepository).save(productAssert);
//        assertEquals(productTemplate,resultPtoduct);
    }

//    @Test
//    void edit() {
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(productTemplate));
//        Product resultProduct = productService.edit(3L, productTemplate);
//        verify(productRepository).findById(3L);
//        assertEquals(productTemplate,resultProduct);
//    }
//
//    @Test
//    void getAll() {
//        when(productRepository.findAll()).thenReturn(productTemplatetList);
//        List<Product> productList = productService.getAll();
//        verify(productRepository).findAll();
////        assertEquals(productTemplatetList, productList);
//        compareProduct(productTemplatetList.get(0), productList.get(0));
//    }
//
//    private void compareProduct(Product productTemp, Product resultProduct) {
//        assertAll(
//            () -> assertEquals(productTemplate.getId(), resultProduct.getId()),
//            () -> assertEquals(productTemplate.getName(), resultProduct.getName()),
//            () -> assertEquals(productTemplate.getStatus(), resultProduct.getStatus()),
//            () -> assertEquals(productTemplate.getCurrencyCode(), resultProduct.getCurrencyCode()),
//            () -> assertEquals(productTemplate.getInterestRate(), resultProduct.getInterestRate()),
//            () -> assertEquals(productTemplate.getProductLimit(), resultProduct.getProductLimit()),
//            () -> assertEquals(productTemplate.getCreatedAt(), resultProduct.getCreatedAt()),
//            () -> assertEquals(productTemplate.getUpdatedAt(), resultProduct.getUpdatedAt()),
//            () -> assertEquals(productTemplate.getManager(), resultProduct.getManager())
//        );
//    }
//
//    @Test
//    void getAllByStatus() {
//        when(productRepository.findAllByStatus(ProductStatus.ACTIVE)).thenReturn(productTemplatetList);
//        List<Product> productList = productService.getAllByStatus(String.valueOf(ProductStatus.ACTIVE));
//        verify(productRepository).findAllByStatus(ProductStatus.ACTIVE);
//        compareProduct(productTemplatetList.get(0),productList.get(0));
//    }
//
//    @Test
//    void getAllByStatusAndCurrencyCode() {
//        when(productRepository.findAllByStatusAndCurrencyCode(ProductStatus.ACTIVE, CurrencyCode.RUB)).thenReturn(productTemplatetList);
//        List<Product> productList = productService.getAllByStatusAndCurrencyCode(String.valueOf(ProductStatus.ACTIVE), String.valueOf(CurrencyCode.RUB));
//        verify(productRepository).findAllByStatusAndCurrencyCode(ProductStatus.ACTIVE, CurrencyCode.RUB);
//        compareProduct(productTemplatetList.get(0),productList.get(0));
//    }
//
//    @Test
//    void getAllByStatusAndCurrencyCodeAndRate() {
////        when(productRepository.getAllByStatusAndCurrencyCodeAndRate(any(),any(), any())).thenReturn(productTemplatetList);
//        when(productRepository.findAllByStatusAndCurrencyCodeAndRate(ProductStatus.ACTIVE, CurrencyCode.RUB, 17.25d)).thenReturn(productTemplatetList);
//        List<Product> productList = productService.getAllByStatusAndCurrencyCodeAndRate(String.valueOf(ProductStatus.ACTIVE), String.valueOf(CurrencyCode.RUB), 17.25d);
//        compareProduct(productTemplatetList.get(0),productList.get(0));
//    }
}