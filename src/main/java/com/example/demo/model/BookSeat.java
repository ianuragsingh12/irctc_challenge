/*
 */
package com.example.demo.model;

import com.example.demo.component.Coach;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author ianur
 */
@Data
public class BookSeat {

    private Long seatNo;
    private Long coachNo;
    private Long trainNo;
    private Coach coachType;
    private LocalDate date;
    private Long userId;
}
