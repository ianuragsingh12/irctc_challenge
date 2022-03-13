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
public class AvailableSeatsResponse {

    private Long coachNo;
    private Long seatNo;
    private Coach coachType;
}
