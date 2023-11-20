package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Client;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ClientStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long>  {
    List<Client> findAllByManager_Id(Long id);
    Optional<Client> findClientByPhone(String phone);
    List<Client> findClientByAddress(String address);
    List<Client> findClientByStatus(ClientStatus status);

    List<Client> findClientByCreatedAtIsBetween (LocalDateTime dateTimeWith, LocalDateTime dateTimeTo );

}
