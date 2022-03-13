/*
 */
package com.example.demo.service;

import com.example.demo.entity.Seat;
import com.example.demo.model.BookSeat;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ianur
 */
public interface UserService {

    List<Seat> availableSeatInCoach(Long coachNo, Long tNo, LocalDate date);

    Seat bookSeat(BookSeat book);

    public boolean isAdmin(Long userId);

    public boolean userExists(Long userId);
}
