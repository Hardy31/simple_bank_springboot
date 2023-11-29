package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository agreementRepository;

    public Agreement getById(long id) {
        return agreementRepository.findById(id).get();
    }

    @Cacheable("Agreements")
    public List<Agreement> getAll() {
        return (List<Agreement>) agreementRepository.findAll();
    }

    @CacheEvict("Agreements")
    public Agreement create(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    @CacheEvict("Agreements")
    public void delete(Long id) {
        agreementRepository.deleteById(id);
    }

    @CacheEvict("Agreements")
    public Agreement edit(Agreement agreement) {
        return agreementRepository.save(agreement);
    }
}


