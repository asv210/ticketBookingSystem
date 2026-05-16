package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;
    private String coach;

    private Integer seatNumber;

    private boolean booked;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @OneToMany(
            mappedBy = "seat",
    cascade = CascadeType.ALL
    )
    private List<SeatBooking> bookings;
}
