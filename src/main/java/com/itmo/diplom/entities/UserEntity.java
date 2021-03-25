package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "diplom")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "login", nullable = false, length = 255)
    private String login;
    @Basic
    @Column(name = "passwd", nullable = false, length = 255)
    private String passwd;
    @Basic
    @Column(name = "role", nullable = true)
    private Integer role;
    @OneToMany(mappedBy = "userByCashierId")
    private Collection<MenuEntity> menusById;

    @OneToOne(mappedBy = "userByUserId")
    private UserWorktimeEntity userWorktimeById;
}
