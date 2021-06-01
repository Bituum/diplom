package com.itmo.diplom.entity;

import lombok.*;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
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
    @NotNull
    private String nameOfDish;

    @Basic
    @Column(name = "is_active")
    private boolean isActive;


    @Basic
    @Column(name = "time_to_cooking")
    private LocalTime timeToCooking;

    @Basic
    @Column(name = "ordered")
    private int ordered = 0;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @Nullable
    @JoinTable(name = "product_dishes",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<ProductsEntity> productsEntity;

    public void setIsActive(boolean b) {
        isActive = b;
    }

    public void increaseOrderedCounter(){
        ordered++;
    }

    public void reduceOrderedCounter(){
        ordered--;
    }


}
