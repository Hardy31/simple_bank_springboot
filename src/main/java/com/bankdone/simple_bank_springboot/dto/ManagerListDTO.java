package com.bankdone.simple_bank_springboot.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Приведенный фрагмент кода включает несколько аннотаций, обычно используемых в библиотеке Lombok.
 * для автоматического создания шаблонного кода для класса.
 *
 * @NoArgsConstructor: эта аннотация создает для класса конструктор без аргументов.
 * @AllArgsConstructor: эта аннотация создает конструктор с параметрами для всех полей класса.
 * @Getter: эта аннотация генерирует методы получения для всех нестатических полей в классе.
 * @Setter: эта аннотация генерирует методы установки для всех нефинальных нестатических полей в классе.
 * @FieldDefaults(level = AccessLevel.PRIVATE): эта аннотация устанавливает уровень доступа по умолчанию.
 * для полей в классе закрыты. Обычно он используется в сочетании с другими аннотациями.
 * из Ломбока, чтобы указать желаемый уровень доступа к полям.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerListDTO {
    List<ManagerDTO> managerDTOList;
}
