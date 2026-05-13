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
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String trainId;
    private String trainNo;
    @OneToMany(mappedBy = "train")
    private List<Seat> seats;
    @OneToMany(mappedBy = "train")
    private List<StationSchedule> schedules;
}
