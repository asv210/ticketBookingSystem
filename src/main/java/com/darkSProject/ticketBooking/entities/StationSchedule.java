package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class StationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String stationName;

    private Date arrivalTime;

    private Date departureTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
}
