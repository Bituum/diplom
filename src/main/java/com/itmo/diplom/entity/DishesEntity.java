package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dishes", schema = "diplom")
public class DishesEntity{
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_dishes",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<ProductsEntity> productsEntity;
}
