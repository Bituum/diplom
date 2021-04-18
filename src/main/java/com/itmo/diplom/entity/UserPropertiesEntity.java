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
/*@IdClass(UserPropertiesEntity.class)*/
@Table(name = "user_properties", schema = "diplom")
public class UserPropertiesEntity{
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    @Size(min=1, max=255)
    @NotNull
    private String name;
    @Basic
    @Column(name = "second_name", nullable = false, length = 255)
    @Size(min=1, max=255)
    @NotNull
    private String secondName;
    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    @Size(min=1, max=255)
    @NotNull
    private String surname;
    @Basic
    @Column(name = "sex", nullable = false, length = 1)
    @Size(min=1, max=1)
    @NotNull
    private String sex;

    @Basic
    @Column(name = "salary", nullable = true)
    @Negative(message = "Запралта не может быть меньше 0!")
    private Integer salary;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
