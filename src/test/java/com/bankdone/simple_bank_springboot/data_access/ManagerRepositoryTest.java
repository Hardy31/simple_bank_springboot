package com.bankdone.simple_bank_springboot.data_access;

import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;



    @Test
    void should_find_no_Manajers_if_repository_is_NotNull(){
        Iterable managers = managerRepository.findAll();
    log.info("Iterable managers -{}", managers);
        assertThat(managers).hasSize(9);
        Assertions.assertNotNull(managers);
    }
    @Test
    void should_store_a_Manager() {
        Manager createdManager = CreatorFakeEntity.createFakeManager();
        log.info(" should_store_a_Manager() - createdManager {}", createdManager);
        Manager savedManager = managerRepository.save(createdManager);
        log.info(" should_store_a_Manager() -  savedManager {}", savedManager);
        assertThat(savedManager).isEqualTo(createdManager).isNotIn("id");
    }


    @Test
    public void should_find_all_managers() {
        Manager createdManager = CreatorFakeEntity.createFakeManager();
        Manager savedManager = managerRepository.save(createdManager);
        Iterable allManagers = managerRepository.findAll();
        assertThat(allManagers).hasSizeBetween(10, 50);     //При создании БД гениротся 9 записей Manager
    }

    @Test
    public void should_find_Manager_by_id() {
        Manager createdManager = CreatorFakeEntity.createFakeManager();
        Manager savedManager = managerRepository.save(createdManager);
        Manager foundManager = managerRepository.findById(savedManager.getId()).get();
        Assertions.assertEquals(foundManager, savedManager);
    }

    @Test
    public void should_update_Manager() {
        Manager createdManager = CreatorFakeEntity.createFakeManager();
        Manager savedManager = managerRepository.save(createdManager);
        Manager updateManager = savedManager;
        updateManager.setFirstName("NewName");
        Manager foundManager = managerRepository.save(updateManager);
        Assertions.assertEquals(foundManager.getFirstName(), "NewName");
    }


    @Test
    public void should_delete_Manager_by_id() {
        Manager createdManager = CreatorFakeEntity.createFakeManager();
        Manager savedManager = managerRepository.save(createdManager);
        List<Manager> managersBeforDel = (List<Manager>)managerRepository.findAll();
        log.info("savedManager -{}", savedManager);
        managerRepository.deleteById(savedManager.getId());
        List<Manager> managersAfterDel = (List<Manager>) managerRepository.findAll();

        assertThat(managersAfterDel).hasSize(managersBeforDel.size() -1);

    }

    @Test
    public void should_find_Manager_by_status() {
        Manager createdManager = CreatorFakeEntity.createFakeManager();
        Integer sizeStatus = ((List<Manager>) managerRepository.findAllByStatus(createdManager.getStatus())).size();
        Manager savedManager = managerRepository.save(createdManager);
        Integer sizeStatusNew = ((List<Manager>)managerRepository.findAllByStatus(createdManager.getStatus())).size();
        Assertions.assertEquals(sizeStatus, sizeStatusNew-1);
    }


}