package com.itmo.diplom.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "role_name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
    public Role(Integer x) {
        this.id = x;
    }

    public Role(int i, String user) {
        this.id = i;
        this.name = user;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
