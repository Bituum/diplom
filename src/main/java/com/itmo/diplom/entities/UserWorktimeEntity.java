package com.itmo.diplom.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@IdClass(UserWorktimeEntity.class)
@Table(name = "user_worktime", schema = "diplom")
public class UserWorktimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Basic
    @Column(name = "start_time", nullable = false)
    private Time startTime;
    @Basic
    @Column(name = "end_time", nullable = false)
    private Time endTime;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity userok;

}
