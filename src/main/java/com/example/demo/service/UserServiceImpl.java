/*
 */
package com.example.demo.service;

import com.example.demo.component.UserType;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Dates;
import com.example.demo.entity.Seat;
import com.example.demo.model.BookSeat;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.DatesRepository;
import com.example.demo.repository.SeatsRepository;
import com.example.demo.repository.UserRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ianur
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SeatsRepository seatsRepository;

    @Autowired
    private DatesRepository datesRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isAdmin(Long userId) {
        return (userRepository.existsByUserIdAndType(userId, UserType.ADMIN));
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public List<Seat> availableSeatInCoach(Long coachNo, Long tNo, LocalDate date) {
        List<Seat> seats = seatsRepository.findByCoachNoAndTrainNo(coachNo, tNo);

        List<Seat> res = new ArrayList<>();
        for (Seat s : seats) {
            if (!datesRepository.existsByDateAndSid(date, s.getSid())) {
                res.add(s);
            }
        }
        return res;
    }

    @Override
    public Seat bookSeat(BookSeat book) {

        Seat s = seatsRepository
                .findBySeatNoAndCoachNoAndTrainNo(book.getSeatNo(),
                        book.getCoachNo(),
                        book.getTrainNo());

        if (s == null) {
            //throw invalid seat
            throw new RuntimeException("Invalid seat");
        } else {
            Dates date = datesRepository.findByDateAndSid(book.getDate(), s.getSid());

            if (date == null) {
                // seat is available
                book(book, s.getSid());
                return s;
            } else {
                if (date.getDate().isEqual(book.getDate())) {
                    // seat is booked
                    return null;
                } else {
                    // seat is available
                    book(book, s.getSid());
                    return s;
                }
            }
        }
    }

    @Transactional
    public Booking book(BookSeat seat, Long sid) {

        Dates d = new Dates();
        d.setDate(seat.getDate());
        d.setSid(sid);

        d = datesRepository.save(d);

        Booking b = new Booking();
        b.setUserId(seat.getUserId());
        b.setDid(d.getDid());

        return bookingRepository.save(b);
    }
}
