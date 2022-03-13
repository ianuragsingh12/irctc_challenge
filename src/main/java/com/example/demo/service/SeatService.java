/*
 */
package com.example.demo.service;

import com.example.demo.entity.Seat;
import java.util.List;

/**
 *
 * @author ianur
 */
public interface SeatService {

    List<Seat> availableSeates();

    List<Seat> bookedSeates();
}
