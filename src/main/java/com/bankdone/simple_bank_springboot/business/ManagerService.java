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
    private Manager manager;

    @Cacheable("Manager")
    public List<Manager> getAllManagers() {
        return (List<Manager>) managerRepository.findAll();
    }

    public List<Manager> getAllManagersByStatus(ManagerStatus manStatus) {
//        System.out.println("stringStatus " + manStatus);
        return (List<Manager>) managerRepository.findAllByStatus(manStatus);
    }

    public Optional<Manager> getManagerById(Long id){
        return managerRepository.findById(id);
    }


    public void deleteById(Long id){
        managerRepository.deleteById(id);
    }

    public List<Manager> getAllManagersWorkingWith(LocalDateTime dateTime) {
        return  managerRepository.findAllByCreatedAtAfter(dateTime);}

    public List<Manager> getAllManagersWorkingWithTo(LocalDateTime dateTimeWith,  LocalDateTime dateTimeTo) {
        return  managerRepository.findAllByCreatedAtIsBetween(dateTimeWith, dateTimeTo);}

//    @Cacheable("Manager")
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    public Manager editManager(Long id, Manager manager){
        Manager updateManager = new Manager();
        managerRepository.save(manager);
        updateManager = managerRepository.findById(id).get();
        return updateManager;
    }

    public Manager getManagersByFIO(Manager manager) {
        String firstName = manager.getFirstName();
        String lastName = manager.getLastName();
       Manager managerfromDB =  managerRepository.findByFirstNameAndLastName(firstName, lastName);
        if(managerfromDB == null){
            manager.setStatus(ManagerStatus.ACTIVE);
            manager.setCreatedAt(LocalDateTime.now());
            manager = create(manager);
            System.out.println(" Добро пожаловать в наш коллектив!");
        }else if(managerfromDB.getStatus().equals(ManagerStatus.DISMISSED)){
            manager = managerfromDB;
            System.out.println(" В приеме на работу отказать! Был ранее уволен");
        }else{
            manager = managerfromDB;
            System.out.println(" Данный человек уже работает в Банке");
        }
        return manager;
    }
}
