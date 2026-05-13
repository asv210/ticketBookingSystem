package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
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
}
