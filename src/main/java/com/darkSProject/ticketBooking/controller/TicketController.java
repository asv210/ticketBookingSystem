package com.darkSProject.ticketBooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @GetMapping("/my-bookings")
    public String myBookings(){
        return "Protected Route Accessed";
    }
}
