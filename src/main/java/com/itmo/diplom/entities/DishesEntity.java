package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;


@Data
@NoArgsConstructor
@Entity
@Table(name = "dishes", schema = "diplom")
public class DishesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name_of_dish", nullable = false)
    private int nameOfDish;
    @Basic
    @Column(name = "time_to_cooking", nullable = false)
    private Time timeToCooking;
    @ManyToOne
    @JoinColumn(name = "products_from_storage", referencedColumnName = "id")
    private StorageEntity storageByProductsFromStorage;
    @OneToMany(mappedBy = "dishesByDish")
    private Collection<MenuEntity> menusById;
}
