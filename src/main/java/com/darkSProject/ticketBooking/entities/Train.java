package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String trainId;

    @Column(unique = true)
    private String trainNo;
    private String trainName;
    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL
    )
    private List<Seat> seats;
    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL
    )
    private List<StationSchedule> schedules;
}
