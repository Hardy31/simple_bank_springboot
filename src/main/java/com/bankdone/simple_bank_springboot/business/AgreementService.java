package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.dto.AgreementCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AgreementDTO;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface AgreementService {
    AgreementDTO create(AgreementCreateDTO agreementCreateDTO);
    AgreementDTO getById(long id);

    AgreementDTO edit(AgreementDTO agreementDTO);

    void delete(Long id);

    List<AgreementDTO> getAll();

}


