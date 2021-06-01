package com.itmo.diplom.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_worktime", schema = "diplom")
public class UserWorktimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Basic
    @Column(name = "start_time", nullable = false)
    @NotNull
    private LocalTime startTime;
    @Basic
    @Column(name = "end_time", nullable = false)

    private LocalTime endTime;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity userok;

    @Transient
    private boolean doesHeWork = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
