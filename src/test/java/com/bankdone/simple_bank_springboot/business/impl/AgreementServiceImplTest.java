package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.data_access.ClientRepository;
import com.bankdone.simple_bank_springboot.data_access.ProductRepository;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import com.bankdone.simple_bank_springboot.mapper.AgreementMapper;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for AgrimentServiceImplTest")
@Slf4j
class AgreementServiceImplTest {
    @Mock
    private AgreementRepository agreementRepository;
    private ProductRepository productRepository;

    private AccountRepository accountRepository;

    private AgreementMapper agreementMapper;
    private Agreement agreementTemplate;
    private AgreementService agrimentServiceImpl;

    private List<Agreement> agreemenTemplatetList;

//    @BeforeEach
//    public void setUp() {
//        agreementTemplate = CreatorFakeEntity.getFakeAgreement();
//        log.info("AgrimentServiceImplTest- setUp - agreementTemplate: {}", agreementTemplate);
//        agrimentServiceImpl = new AgreementServiceImpl(agreementRepository, productRepository, accountRepository, agreementMapper);
//        agreemenTemplatetList = new ArrayList<>(List.of(agreementTemplate));
//        AgreeementCreateDTO agreeementCreateDTO
//    }
//
//    @Test
//    void create() {
//        when(agreementRepository.save(any())).thenReturn(agreementTemplate);
//        agrimentServiceImpl.create();
//
//        verify(agreementRepository).save(agreementTemplate);
//        assertEquals(agreementTemplate, result);
//    }
//
//    @Test
//    void getById() {
//        when(agreementRepository.findById(anyLong())).thenReturn(Optional.of(agreementTemplate));
//        Agreement result = agrimentServiceImpl.getById(6L);
//        verify(agreementRepository).findById(anyLong());
//        assertEquals(agreementTemplate, result);
//    }
//
//    @Test
//    void getAll() {
//        when(agreementRepository.findAll()).thenReturn(agreemenTemplatetList);
//        List<Agreement> resultList = agrimentServiceImpl.getAll();
//        verify(agreementRepository).findAll();
//        assertEquals(agreemenTemplatetList, resultList);
//
//    }
//
//    @Test
//    void delete() {
//        doNothing().when(agreementRepository).deleteById(anyLong());
//        agrimentServiceImpl.delete(6L);
//        verify(agreementRepository).deleteById(anyLong());
//    }
//
//    @Test
//    void edit() {
//        when(agreementRepository.save(any())).thenReturn(agreementTemplate);
//        Agreement result = agrimentServiceImpl.edit(agreementTemplate);
//        verify(agreementRepository).save(agreementTemplate);
//        assertEquals(agreementTemplate, result);
//    }

}
