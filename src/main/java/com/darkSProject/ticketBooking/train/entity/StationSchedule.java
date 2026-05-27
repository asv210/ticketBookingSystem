package com.darkSProject.ticketBooking.train.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String scheduleId;

    private Integer stationOrder;

    private String stationName;

    private Date arrivalTime;

    private Date departureTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
}
