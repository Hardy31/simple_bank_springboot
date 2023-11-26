package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
}
