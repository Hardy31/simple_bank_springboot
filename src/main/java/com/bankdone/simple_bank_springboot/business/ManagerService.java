package com.bankdone.simple_bank_springboot.business;


import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Cacheable("Manager")
    public List<Manager> listAll() {
        return (List<Manager>) managerRepository.findAll();
    }

    public List<Manager> getAllManagersByStatus(ManagerStatus manStatus) {
        System.out.println("stringStatus " + manStatus);
        return (List<Manager>) managerRepository.findAllManagersByStatus(manStatus);
    }

    public Optional<Manager> getManagerById(Integer id){
        return managerRepository.findById(id);
    }

    public void deleteById(Integer id){
        managerRepository.deleteById(id);
    }

}
