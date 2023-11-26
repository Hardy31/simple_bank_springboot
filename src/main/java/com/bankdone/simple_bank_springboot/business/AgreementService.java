package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AgreementRepository;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgreementService {

    @Autowired
    AgreementRepository agreementRepository;
    public Agreement getById(long id) {
        return agreementRepository.findById(id).get();
    }

    public List<Agreement> getAll() {
        return (List<Agreement>) agreementRepository.findAll();
    }

    public Agreement create(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    public void delete(Long id) {
        agreementRepository.deleteById(id);
    }

    public Agreement edit(Agreement agreement) {
        return agreementRepository.save(agreement);
    }
}


