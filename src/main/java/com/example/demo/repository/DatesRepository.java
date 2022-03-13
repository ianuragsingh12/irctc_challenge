/*
 */
package com.example.demo.repository;

import com.example.demo.entity.Dates;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ianur
 */
@Repository
public interface DatesRepository extends JpaRepository<Dates, Long> {

    Dates findBySid(Long sid);

    // unique combination
    Dates findByDateAndSid(LocalDate date, Long sid);
    
    boolean existsByDateAndSid(LocalDate date, Long sid);
}
