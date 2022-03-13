/*
 */
package com.example.demo.controller;

import com.example.demo.entity.Seat;
import com.example.demo.model.BookMultiSeat;
import com.example.demo.model.BookSeat;
import com.example.demo.service.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ianur
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/seats/available")
    public List<Seat> availableSeatesInCoach(@RequestParam(name = "coachNo") Long coachNo,
            @RequestParam(name = "trainNo") Long trainNo,
            @RequestParam(name = "date") LocalDate date) {
        return userService.availableSeatInCoach(coachNo, trainNo,date);
    }
    
    @GetMapping("/book")
    public Map bookMultipleSeates(@RequestBody BookMultiSeat multipleBook) {
        
        Map<String, List<Seat>> map = new HashMap<>();
        List<Seat> booked = new ArrayList<>();
        List<Seat> nbooked = new ArrayList<>();
        
        for (BookSeat bs : multipleBook.getBook()) {
            Seat s = userService.bookSeat(bs);
            if (s == null) {
                nbooked.add(s);
            } else {
                booked.add(s);
            }
        }
        map.put("booked", booked);
        map.put("not_booked", nbooked);
        return map;
    }
}
