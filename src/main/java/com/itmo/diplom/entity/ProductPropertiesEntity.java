package com.itmo.diplom.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_properties", schema = "diplom")
public class ProductPropertiesEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id", nullable = false)
    private Integer productsId;
    @Basic
    @Column(name = "name", nullable = false)
    @Size(min=1, max=255)
    @NotNull
    private String name;
    @Basic
    @Column(name = "shelf_life", nullable = false)
    private Date shelfLife;
    @Basic
    @Column(name = "amount", nullable = false)
    @Min(1)
    @NotNull
    private int amount;
    @Basic
    @Column(name = "cost", nullable = false)
    @Min(1)
    @NotNull
    private int cost;
    @Basic
    @Column(name = "calories_per_piece", nullable = false)
    @Min(1)
    @NotNull
    private int caloriesPerPiece;
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "products_id")
    private ProductsEntity product;
}
