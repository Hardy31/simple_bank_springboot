package com.bankdone.simple_bank_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;



  /**
  * Приведенный фрагмент кода включает несколько аннотаций, обычно используемых в библиотеке Lombok.
  *
  * для автоматического создания шаблонного кода для класса.
  * @NoArgsConstructor: эта аннотация создает для класса конструктор без аргументов.
  * @AllArgsConstructor: эта аннотация создает конструктор с параметрами для всех полей класса.
  * @Getter: эта аннотация генерирует методы получения для всех нестатических полей в классе.
  * @Setter: эта аннотация генерирует методы установки для всех нефинальных нестатических полей в классе.
  * @FieldDefaults(level = AccessLevel.PRIVATE): эта аннотация устанавливает уровень доступа по умолчанию.
  * для полей в классе закрыты. Обычно он используется в сочетании с другими аннотациями.
  * из Ломбока, чтобы указать желаемый уровень доступа к полям.
  * <р>
  * shape = JsonFormat.Shape.STRING: указывает, что дата должна быть сериализована и десериализована как строка.
  * шаблон = «yyyy-MM-dd'T'HH:mm:ss.SSSSSS»: определяет шаблон, который будет использоваться для форматирования даты в виде строки.
  * В этом случае шаблон указывает, что дата должна быть отформатирована как «год-месяц-день часы-минуты-секунды: и далее» (например, «2023-05-18 ~»).
  * String id нет так обект будет создаваться по полученным данным
  *  String managerId так как из формы мы получим только Идентификатор менеджера который создает (вводит, ответственный)
  *  за дынный продукт.
  */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateDTO {
    String name;
    String status;
    String currencyCode;
    String interestRate;
    String productLimit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    LocalDateTime updatedAt;
    String managerId;

}
