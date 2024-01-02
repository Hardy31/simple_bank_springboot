package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import com.bankdone.simple_bank_springboot.entity.enums.CurrencyCode;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import com.bankdone.simple_bank_springboot.entity.enums.ProductStatus;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreatorFakeEntity {
    private Product fakeProduct;

    public static Manager createFakeManager(){
        Manager fakeManager = new Manager().builder()
                .firstName("NameF" )
                .lastName("SurnameF")
                .status(ManagerStatus.valueOf("ACTIVE"))
                .createdAt(LocalDateTime.now())
                .build();
        return fakeManager;
    }
    public static Manager getFakeManager(Long id){
        Manager fakeManager = createFakeManager();
        fakeManager.setId(id);
        return fakeManager;
    }

    public static Client creatFakeClient(Long fakeManagerId){
        Client fakeClient = Client.builder()
                .status(ClientStatus.ACTIVE)
                .taxCode("Naccound")
                .firstName("nameClientF")
                .lastName("SurnameClientF")
                .address("addresClientF")
                .email("emailClientF")
                .phone("phoneClientF")
                .createdAt(LocalDateTime.now())
                .manager(getFakeManager(fakeManagerId))
                .build();
        return  fakeClient;
    }
    public static Client getFakeClient(Long fakeClientId){
        Client fakeClient = creatFakeClient(2l);
        fakeClient.setId(fakeClientId);
        return  fakeClient;
    }

    public static Product creatFakeProduct(){
        Product fakeProduct = Product.builder()
                .name("deposit high")
                .status(ProductStatus.ACTIVE)
                .currencyCode(CurrencyCode.RUB)
                .interestRate(17.25)
                .productLimit(100000)
                .createdAt(getFixedLocalDateTime())
                .manager(getFakeManager(2L))
                .build();
        return  fakeProduct;
    }

    public static  Product getFakeProduct(Long fakeProductId){
        Product fakeProduct = creatFakeProduct();
        fakeProduct.setId(fakeProductId);
        return fakeProduct;
    }

    public static LocalDateTime getFixedLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fixedLocalDateTime = LocalDate.parse("1998-07-07", formatter).atStartOfDay();
        return  fixedLocalDateTime;
    }
}
