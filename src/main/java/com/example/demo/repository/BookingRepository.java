/*
 */
package com.example.demo.repository;

import com.example.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ianur
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
