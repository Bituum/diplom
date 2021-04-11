package com.itmo.diplom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dishes", schema = "diplom")
public class DishesEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name_of_dish", nullable = false)
    private String nameOfDish;

    //Count of active orders
    @Transient
    private int isActive = 0;

    @Basic
    @Column(name = "time_to_cooking")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Time timeToCooking;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Nullable
    @JoinTable(name = "product_dishes",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<ProductsEntity> productsEntity;

}
