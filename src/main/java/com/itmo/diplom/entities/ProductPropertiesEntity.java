package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@IdClass(ProductPropertiesEntity.class)
@Table(name = "product_properties", schema = "diplom")
public class ProductPropertiesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Id
    @Column(name = "products_id", nullable = false)
    private Integer productsId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "shelf life", nullable = false)
    private Date shelfLife;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic
    @Column(name = "cost", nullable = false)
    private int cost;
    @Basic
    @Column(name = " calories_per_piece", nullable = false)
    private int caloriesPerPiece;
}
