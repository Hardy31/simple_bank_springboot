package com.bankdone.simple_bank_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


/**
 * Аннотация @Value аналогична @Data за исключением того, что все поля по умолчанию являются закрытыми
 * и окончательными,  * а сеттеры не создаются. Благодаря этому объекты @Value сразу становятся неизменяемыми.
 * Поскольку все поля являются окончательными, конструктора аргументов нет.
 * Вместо этого Lombok использует @AllArgsConstructor. В результате получается полностью функциональный,
 * неизменяемый объект.
 * <р>
 * Аннотация @JsonFormat предоставляется библиотекой Джексона и используется для определения форматирования дат.
 * или другие значения во время сериализации и десериализации JSON.
 * <р>
 * В предоставленном фрагменте кода аннотация @JsonFormat используется со следующими параметрами:
 * <р>
 * shape = JsonFormat.Shape.STRING: указывает, что дата должна быть сериализована и десериализована как строка.
 * шаблон = «гггг-ММ-дд»: определяет шаблон, который будет использоваться для форматирования даты в виде строки.
 * В этом случае шаблон указывает, что дата должна быть отформатирована как «год-месяц-день» (например, «18 января 2024 г.»).
 */

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@Value
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ManagerDTO {
    String id;
    String firstName;
    String lastName;
    String status;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    LocalDateTime updatedAt;
}
