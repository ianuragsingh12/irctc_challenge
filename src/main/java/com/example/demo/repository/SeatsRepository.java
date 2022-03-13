/*
 */
package com.example.demo.repository;

import com.example.demo.entity.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ianur
 */
@Repository
public interface SeatsRepository extends JpaRepository<Seat, Long> {

    boolean existsBySeatNoAndCoachNo(Long sno, Long cno);

    boolean existsBySeatNoAndCoachNoAndTrainNo(Long sno, Long cno, Long tno);

    // unique combination
    Seat findBySeatNoAndCoachNoAndTrainNo(Long sno, Long cno, Long tno);

    List<Seat> findByCoachNoAndTrainNo(Long cno, Long tno);

    //a
    List<Seat> findByTrainNo(Long train_id);

    List<Seat> findByTrainNoAndCoachNo(Long train_id, Long coachId);

    boolean existsByCoachNo(Long coach_id);

    boolean existsByTrainNo(Long train_id);
}
