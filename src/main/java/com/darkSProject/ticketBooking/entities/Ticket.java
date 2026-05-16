package com.darkSProject.ticketBooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;
    private String source;
    private String destination;
    private Date dateOfTravel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.ALL
    )
    private List<SeatBooking> seatBookings;
}
