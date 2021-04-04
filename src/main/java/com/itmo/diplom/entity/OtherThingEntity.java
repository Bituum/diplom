package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "other_thing", schema = "diplom")
public class OtherThingEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @OneToMany(mappedBy = "otherThingByOtherThingId")
    private Collection<StorageEntity> storagesById;
}
