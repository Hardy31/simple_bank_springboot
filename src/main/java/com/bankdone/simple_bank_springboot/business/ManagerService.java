package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ManagerService {

    List<Manager> getAllManagers();

    List<Manager> getAllManagersByStatus(ManagerStatus manStatus);

    Optional<Manager> getManagerById(Long id);

    void deleteManagerById(Long id);

    List<Manager> getAllManagersWorkingWith(LocalDateTime dateTime);

    List<Manager> getAllManagersWorkingWithTo(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo);

    Manager createManager(Manager manager);

    Manager editManager(Long id, Manager manager);

    String getManagersByFIO(Manager manager);

}
