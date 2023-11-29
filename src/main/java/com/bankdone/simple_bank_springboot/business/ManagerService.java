package com.bankdone.simple_bank_springboot.business;


import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Cacheable("Managers")
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public List<Manager> getAllManagersByStatus(ManagerStatus manStatus) {
        return managerRepository.findAllByStatus(manStatus);
    }

    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    @CacheEvict("Managers")
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    public List<Manager> getAllManagersWorkingWith(LocalDateTime dateTime) {
        return managerRepository.findAllByCreatedAtAfter(dateTime);
    }

    public List<Manager> getAllManagersWorkingWithTo(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo) {
        return managerRepository.findAllByCreatedAtIsBetween(dateTimeWith, dateTimeTo);
    }

    @CacheEvict("Managers")
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    @CacheEvict("Managers")
    public Manager editManager(Long id, Manager manager) {
        managerRepository.save(manager);
        Manager updateManager = managerRepository.findById(id).get();
        return updateManager;
    }

    public Manager getManagersByFIO(Manager manager) {
        String firstName = manager.getFirstName();
        String lastName = manager.getLastName();
        Manager managerfromDB = managerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (managerfromDB == null) {
            manager.setStatus(ManagerStatus.ACTIVE);
            manager.setCreatedAt(LocalDateTime.now());
            manager = create(manager);
            System.out.println(" Добро пожаловать в наш коллектив!");
        } else if (managerfromDB.getStatus().equals(ManagerStatus.DISMISSED)) {
            manager = managerfromDB;
            System.out.println(" В приеме на работу отказать! Был ранее уволен");
        } else {
            manager = managerfromDB;
            System.out.println(" Данный человек уже работает в Банке");
        }
        return manager;
    }

}
