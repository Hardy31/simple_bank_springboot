package com.bankdone.simple_bank_springboot.util;

import com.bankdone.simple_bank_springboot.entity.*;
import com.bankdone.simple_bank_springboot.entity.enums.*;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreatorFakeEntity {

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

    public static Agreement createFakeAgreement(){
        Agreement agreement = Agreement.builder()
            .interestRate(17.25)
            .status(AgreementStatus.ACTIVE)
            .sum(150000.00d)
            .updatedAt(getFixedLocalDateTime())
            .product(getFakeProduct(3L))
//            .account(getFakeAccount(3L))
            .build();
        return  agreement;
    }

    public static Agreement getFakeAgreement(){
        Agreement agreement = createFakeAgreement();
        agreement.setId(3L);
        return agreement;
    }

    public static Account createFakeAccount(){
        Account account = Account.builder()
                .name("number")
                .type(AccountType.ESCROW)
                .status(AccountStatus.ACTIVE)
                .balance(1000000.00)
                .code(CurrencyCode.RUB)
                .createdAt(getFixedLocalDateTime())
                .build();
        return account;
    }
    public static Account getFakeAccount(Long id){
        Account account = createFakeAccount();
        account.setId(id);
        account.setName(account.getName() + id);
        return account;
    }


    public static Transaction createFakeTransaction(){
        Transaction transaction = Transaction.builder()
                .type(TransactionType.DEPOSIT)
                .amount(100000d)
                .description("Описание")
                .debitAccount(getFakeAccount(9L))
                .creditAccount(getFakeAccount(8L))
                .createdAt(getFixedLocalDateTime())
                .build();
        return transaction;
    }

    public static  Transaction getFakeTrensaction(Long id){
        Transaction transaction = createFakeTransaction();
        transaction.setId(id);
        return transaction;
    }

    public static LocalDateTime getFixedLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fixedLocalDateTime = LocalDate.parse("1998-07-07", formatter).atStartOfDay();
        return  fixedLocalDateTime;
    }
}
