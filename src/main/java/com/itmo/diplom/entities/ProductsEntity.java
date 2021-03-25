package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "diplom")
public class ProductsEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<StorageEntity> storagesById;
}
