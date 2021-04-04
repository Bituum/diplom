package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "storage", schema = "diplom")
public class StorageEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "other_things", nullable = false)
    private int otherThings;
    @OneToMany(mappedBy = "storageByProductsFromStorage")
    private Collection<DishesEntity> dishesById;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductsEntity productsByProductId;
    @ManyToOne
    @JoinColumn(name = "other_thing_id", referencedColumnName = "id", nullable = false)
    private OtherThingEntity otherThingByOtherThingId;
}
