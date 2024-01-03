package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for AgrimentServiceImplTest")
class AgrimentServiceImplTest {
    @Mock
    private AgreementRepository agreementRepository;

    private Agreement agreementTemplate;

    private void setUp(){
    }

    @Test
    void create() {
    }
}