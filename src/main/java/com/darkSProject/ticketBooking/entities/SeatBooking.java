package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Table
@Entity
@NoArgsConstructor
public class SeatBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bookingId;

    @ManyToOne
    @JoinColumn(
            name = "seat_id",
            nullable = false
    )
    private Seat seat;

    @ManyToOne
    @JoinColumn(
            name = "train_id",
            nullable = false
    )
    private Train train;

    @ManyToOne
    @JoinColumn(
            name = "ticket_id",
            nullable = false
    )
    private Ticket ticket;

    @Column(
            nullable = false
    )
    private Date dateOfTravel;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private String source;

    private String destination;

    private Integer sourceOrder;

    private Integer destinationOrder;

}
