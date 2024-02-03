package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ManagerService {

    List<ManagerDTO> getAllManagers();

    List<ManagerDTO> getAllManagersByStatus(ManagerStatus manStatus);

    ManagerDTO getManagerById(Long id);

    void deleteManagerById(Long id);

    List<ManagerDTO> getAllManagersWorkingWith(LocalDateTime dateTime);

    List<ManagerDTO> getAllManagersWorkingWithTo(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo);

    ManagerDTO createManager(ManagerCreatDTO managerDTO);

    ManagerDTO editManager(Long id, ManagerDTO managerDTO);

    String getManagersByFIO(ManagerDTO ManagerDTO);

}
