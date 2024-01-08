package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.ManagerService;
import com.bankdone.simple_bank_springboot.data_access.ManagerRepository;
import com.bankdone.simple_bank_springboot.entity.Manager;
import com.bankdone.simple_bank_springboot.entity.enums.ManagerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс ManagerServiceImpl является реализацией интерфейса ManagerService.
 * Предоставляет методы для выполнения различных операций, связанных с менеджерами.
 *
 * @Service: эта аннотация используется для указания того, что этот класс является компонентом службы в среде Spring.
 * @RequiredArgsConstructor: эта аннотация взята из библиотеки Lombok и генерирует конструктор с обязательными аргументами.
 * для последних полей. Это позволяет нам внедрять зависимости с помощью внедрения конструктора (взамен @Autowired).
 * @Slf4j: Эта аннотация взята из библиотеки Lombok и генерирует поле для логирования.
 * @Transactional: эта аннотация используется в Spring для определения границ транзакций для методов или классов.
 * При применении к методу или классу это указывает на то, что для аннотированного метода необходимо создать транзакцию.
 * или все методы внутри аннотированного класса.
 * Транзакционные границы гарантируют, что группа операций выполняется как одна атомарная единица.
 * Важно отметить, что аннотацию @Transactional следует применять к методам, изменяющим данные.
 * или выполнить несколько операций с базой данных, чтобы обеспечить целостность и согласованность данных.
 * <p>
 * Класс ManagerServiceImpl реализует интерфейс ManagerService,
 * определяющий контракт на выполнение операций над менеджерами.
 * Реализуя этот интерфейс, класс предоставляет необходимую бизнес-логику для операций, связанных с менеджером.
 * <р>
 * С помощью класса ManagerServiceImpl мы можем извлекать, создавать, обновлять и удалять менеджеров.
 * а также получить менеджеров по статусу и получить всех менеджеров.
 * через использование интерфейса ManagerRepository для доступа к данным.
 *
 * @автор: alex
 * @от :    04.12.2023
 */

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    /**
     * * ManagerRepository : это поле используется для доступа к данным менеджера в базе данных.
     */
    private final ManagerRepository managerRepository;

    /**
     * getAllManagers(): Этот метод возвращает список всех менеджеров.
     * @return List<Manager>
     */
    @Override
    @Cacheable("Managers")
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    /**
     * getAllManagersByStatus(ManagerStatus status): этот метод извлекает всех менеджеров с выбранным статусом .
     * @param status Энум ManagerStatus
     * @return List<Manager>
     */
    @Override
    public List<Manager> getAllManagersByStatus(ManagerStatus status) {
        return managerRepository.findAllByStatus(status);
    }

    /**
     * getManagerById(Long id): этот метод извлекает менеджера по его уникальному идентификатору («id»).<p>
     *  TODO: Он выдает исключение ManagerNotFoundException, если не найден ни один менеджер с указанным идентификатором.
     * @param id Long
     * @return Optional<Manager>
     */
    @Override
    public Manager getManagerById(Long id) {
        return managerRepository.findById(id).get();
    }

    /**
     * deleteById(Long id): этот метод удаляет менеджера по его уникальному идентификатору («id»).<p>
     * TODO: Он выдает исключение ManagerNotFoundException, если не найден ни один менеджер с указанным идентификатором.
     * @param id
     */
    @Override
    @CacheEvict("Managers")
    public void deleteManagerById(Long id) {
        managerRepository.deleteById(id);
    }

    /**
     * getAllManagersWorkingWith LocalDateTime dateTime) -этот метод возвращает список менеджеров принятых на работу после
     * @param dateTime  LocalDateTime дата после которой
     * @return List<Manager>
     */
    @Override
    public List<Manager> getAllManagersWorkingWith(LocalDateTime dateTime) {
        return managerRepository.findAllByCreatedAtAfter(dateTime);
    }

    /**
     * getAllManagersWorkingWithTo(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo) - этот метод возвращает список
     * которые были приняты на работу в период с по
     * @param dateTimeWith
     * @param dateTimeTo
     * @return
     */
    @Override
    public List<Manager> getAllManagersWorkingWithTo(LocalDateTime dateTimeWith, LocalDateTime dateTimeTo) {
        return managerRepository.findAllByCreatedAtIsBetween(dateTimeWith, dateTimeTo);
    }

    /**
     * create(Manager manager): этот метод создает новый менеджер на основе предоставленного. <p>
     * TODO: Manager manager, необходимо меределать на CreateManagerDTO createManagerDTO. <br>
     * @param manager
     * @return Manager
     */
    @Override
    @CacheEvict("Managers")
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    /**
     * editManager(Long id, Manager manager): этот метод обновляет менеджера с указанным идентификатором.
     *  используя информацию, предоставленную в переданном manager. <p>
     *  TODO: Он выдает исключение ManagerNotFoundException, если не найден ни один менеджер с указанным идентификатором. <br>
     * @param id
     * @param manager
     * @return Manager
     */
    @Override
    @CacheEvict("Managers")
    public Manager editManager(Long id, Manager manager) {
        managerRepository.save(manager);
        Manager updateManager = managerRepository.findById(id).get();
        return updateManager;
    }

    /**
     * getManagersByFIO(Manager manager)  этот метод возвращает рекомендации по приему на работу . <p>
     * TODO: Manager manager, необходимо меределать на DataManagerDTO(еще подумать над именем класса) dataManagerDTO . <br>
     * @param manager Manager
     * @return String - метод вызывается при принятии на работу. DISMISSED- статус увольнения по статье.
     */
    @Override
    public String getManagersByFIO(Manager manager) {
        String firstName = manager.getFirstName();
        String lastName = manager.getLastName();
        StringBuilder result = new StringBuilder();
        result.append(firstName).
                append(" ").
                append(lastName).
                append(" - ");
        Manager managerfromDB = managerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (managerfromDB == null) {
            manager.setStatus(ManagerStatus.ACTIVE);
            manager.setCreatedAt(LocalDateTime.now());
            manager = createManager(manager);
            return result.append(" Добро пожаловать в наш коллектив!").toString();
        } else if (managerfromDB.getStatus().equals(ManagerStatus.DISMISSED)) {
            manager = managerfromDB;
            return result.append(" В приеме на работу отказать! Был ранее уволен!").toString();
        }
        manager = managerfromDB;
        return result.append(" Данный человек уже работает в Банке или является Однофомильцем!").toString();
    }
}
