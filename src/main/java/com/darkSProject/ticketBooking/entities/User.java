package com.darkSProject.ticketBooking.entities;

import java.util.List;

@Entity
public class User {
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketBooked;
    private String userId;
}
