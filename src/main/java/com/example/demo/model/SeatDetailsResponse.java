/*
 */
package com.example.demo.model;

import com.example.demo.component.Coach;
import lombok.Data;

/**
 *
 * @author ianur
 */
@Data
public class SeatDetailsResponse {

    private Long trainID;

    private Long coachId;

    private Coach coachType;

    private Long seatNumber;
}
