package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "second_name", nullable = false, length = 255)
    private String secondName;
    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    private String surname;
    @Basic
    @Column(name = "sex", nullable = false, length = 1)
    private String sex;
    @Basic
    @Column(name = "salary", nullable = true)
    private Integer salary;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
