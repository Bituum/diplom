package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Data
@NoArgsConstructor
@Entity
//@IdClass(UserWorktimeEntity.class)
@Table(name = "user_worktime", schema = "diplom")
public class UserWorktimeEntity implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Id
    @Id
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Integer id;
    @Basic
    @Column(name = "start_time", nullable = false)
    private Time startTime;
    @Basic
    @Column(name = "end_time", nullable = false)
    private Time endTime;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity userok;

}
