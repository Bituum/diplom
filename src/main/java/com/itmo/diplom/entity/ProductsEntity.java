package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "diplom")
public class ProductsEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "small_description")
    @Basic
    private String productDescription;

    @OneToOne(mappedBy = "product", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProductPropertiesEntity productProperties;
}
