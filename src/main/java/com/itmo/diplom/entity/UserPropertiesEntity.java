package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_properties", schema = "diplom")
public class UserPropertiesEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    @Size(min=1, max=255, message = "Имя не может быть таким")
    @NotNull(message = "Имя не может быть таким")
    private String name;
    @Basic
    @Column(name = "second_name", nullable = false, length = 255)
    @Size(min=1, max=255, message = "Фамилия не может быть такой")
    @NotNull(message = "Фамилия не может быть такой")
    private String secondName;
    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    @Size(min=1, max=255, message = "Отчетство не может быть такой")
    @NotNull(message = "Отчетство не может быть пустой")
    private String surname;
    @Basic
    @Column(name = "sex", nullable = false, length = 1)
    @Size(min=1, max=1, message = "Пол может быть либо м, либо ж")
    private String sex;

    @Basic
    @Column(name = "salary", nullable = true)
    @NotNull(message = "Зарплата не может равнятся нулю")

    private Integer salary;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
