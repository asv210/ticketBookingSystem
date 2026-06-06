package com.darkSProject.ticketBooking.ticket.entity;

import com.darkSProject.ticketBooking.train.entity.Seat;
import com.darkSProject.ticketBooking.train.entity.Train;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Setter
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
    private LocalDateTime dateOfTravel;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private String source;

    private String destination;

    private Integer sourceOrder;

    private Integer destinationOrder;

}
