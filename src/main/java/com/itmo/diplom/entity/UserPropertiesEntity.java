package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
