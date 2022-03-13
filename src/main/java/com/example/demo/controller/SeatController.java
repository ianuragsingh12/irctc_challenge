/*
 */
package com.example.demo.controller;

import com.example.demo.entity.Seat;
import com.example.demo.service.SeatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ianur
 */
@RestController
@RequestMapping("/api/seats/")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/available")
    public List<Seat> availableSeates() {
        return seatService.availableSeates();
    }

    @GetMapping("/booked")
    public List<Seat> bookedSeates() {
        return seatService.bookedSeates();
    }
}
