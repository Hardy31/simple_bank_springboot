package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.ManagerCreatDTO;
import com.bankdone.simple_bank_springboot.dto.ManagerDTO;
import com.bankdone.simple_bank_springboot.entity.Manager;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Интерфейс ManagerMapper — это интерфейс картографа, использующий библиотеку MapStruct. Он предоставляет методы сопоставления
 * для преобразования между объектами `Manager` и объектами передачи данных `ManagerDTO`.
 * <р>
 * `@Mapper(comComponentModel = "spring", imports = LocalDateTime.class)`: эта аннотация взята из библиотеки MapStruct.
 * и указывает, что этот интерфейс следует рассматривать как компонент преобразователя.
 * Атрибут `comComponentModel="spring"` указывает, что Spring должен управлять жизненным циклом компонента сопоставления.
 * Атрибут `imports = LocalDateTime.class` указывает, что класс `LocalDateTime` должен быть импортирован для использования.
 * в выражениях сопоставления.
 * <р>
 * `ManagerDTO toDTO(Менеджер-менеджер)`:
 * Этот метод сопоставляет сущность Manager с ManagerDTO.
 * <р>
 * `Менеджер toEntity(ManagerDTO мanagerDTO)`:
 * Этот метод сопоставляет `ManagerDTO` с сущностью `Manager`.
 * <р>
 * `List<ManagerDTO> managersToManagersDTO(List<Manager> managers)``:
 * Этот метод сопоставляет список объектов Manager со списком объектов ManagerDTO.
 * <р>
 * `@Mapping(target = "createdAt", выражение = "java(new LocalDateTime(System.currentTimeMillis())))`:
 * Эта аннотация используется в методе createToEntity для установки свойства createAt объекта Manager.
 * Он использует выражение сопоставления для создания нового объекта Timestamp, представляющего текущее системное время.
 * <р>
 * `Менеджер createToEntity(CreateManagerDTO ManagerDTO)`:
 * Этот метод сопоставляет CreateManagerDTO с сущностью Manager, включая установку свойства createAt с помощью
 * выражение сопоставления, определенное аннотацией `@Mapping`.
 */

@Mapper(componentModel = "spring", /*uses = UuidMapper.class,*/ imports = LocalDateTime.class)

public interface ManagerMapper {
    ManagerDTO convertToDTO(Manager manager);
    Manager convertToEntity(ManagerDTO managerDTO);

     List<ManagerDTO> convertToManageDTOList(List<Manager> managerList);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Manager createToEntity(ManagerCreatDTO managerCreatDTO);

}
