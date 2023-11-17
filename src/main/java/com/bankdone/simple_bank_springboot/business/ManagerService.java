package com.bankdone.simple_bank_springboot.business;


import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Cacheable("Manager")
    public List<Manager> getAllManagers() {
        return (List<Manager>) managerRepository.findAll();
    }

    public List<Manager> getAllManagersByStatus(ManagerStatus manStatus) {
        System.out.println("stringStatus " + manStatus);
        return (List<Manager>) managerRepository.findAllByStatus(manStatus);
    }

    public Optional<Manager> getManagerById(Long id){
        return managerRepository.findById(id);
    }

    @Cacheable("Manager")
    public void deleteById(Long id){
        managerRepository.deleteById(id);
    }

    public List<Manager> getAllManagersWorkingWith(LocalDateTime dateTime) {
        return  managerRepository.findAllByCreatedAtAfter(dateTime);}

    public List<Manager> getAllManagersWorkingWithTo(LocalDateTime dateTimeWith,  LocalDateTime dateTimeTo) {
        return  managerRepository.findAllByCreatedAtIsBetween(dateTimeWith, dateTimeTo);}

//    @Cacheable("Manager")
    public Manager create(Manager manager) {
        manager = managerRepository.save(manager);
        return manager;
    }

    public Manager editManager(Long id, Manager manager){
        Manager updateManager = new Manager();
        managerRepository.save(manager);
        updateManager = managerRepository.findById(id).get();
        return updateManager;
//        managerRepository.
    }
}
