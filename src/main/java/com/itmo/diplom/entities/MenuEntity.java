package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "menu", schema = "diplom")
public class MenuEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "dish", referencedColumnName = "id", nullable = false)
    private DishesEntity dishesByDish;
    @ManyToOne
    @JoinColumn(name = "cashier_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByCashierId;

}
