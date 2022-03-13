/*
 */
package com.example.demo.entity;

import com.example.demo.component.Coach;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author ianur
 */
@Data
@Entity
public class Seat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private Long seatNo;

    private Long coachNo;

    private Long trainNo;

    private Coach coachtype;
}
