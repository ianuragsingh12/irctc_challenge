/*
 */
package com.example.demo.service;

import com.example.demo.component.Coach;
import com.example.demo.entity.Seat;
import com.example.demo.model.SeatDetailsResponse;
import com.example.demo.repository.SeatsRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ianur
 */
@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private SeatsRepository seatsRepository;

    @Override
    public boolean validTrainID(Long trainId) {
        return seatsRepository.existsByTrainNo(trainId);
    }

    @Override
    public String addCoach(Long adminId, Long trainId, Coach coachType, List<Long> coachIDs) {

        List<String> response = new ArrayList<String>();

        //create new Coaches in Seats
        for (Long coachId : coachIDs) {
            if (seatsRepository.existsByCoachNo(coachId)) {
                response.add("Coach ID " + coachId + " already exists.");
            } else {
                // find seat count for coach type
                Long seatcount = coachType.getCount();
                LocalDate dt = LocalDate.now();
                for (long i = 0; i < seatcount; i++) {
                    Seat coach = new Seat();
                    coach.setSeatNo(i);
                    coach.setCoachNo(coachId);
                    coach.setCoachtype(coachType);
                    coach.setTrainNo(trainId);
                }

            }
        }
        return response.toString();
    }

    @Override
    public List<SeatDetailsResponse> viewSeatsByTrain(Long trainId) {
        List<Seat> allSeats = seatsRepository.findByTrainNo(trainId);
        List<SeatDetailsResponse> response = new ArrayList<SeatDetailsResponse>();
        for (Seat seat : allSeats) {
            SeatDetailsResponse resSeat = new SeatDetailsResponse();
            resSeat.setCoachId(seat.getCoachNo());
            resSeat.setCoachType(seat.getCoachtype());
            resSeat.setSeatNumber(seat.getSeatNo());
            resSeat.setTrainID(trainId);
            response.add(resSeat);
        }
        return response;
    }

    @Override
    public List<SeatDetailsResponse> viewSeatsByCoach(Long trainId, Long coachId) {
        List<Seat> allSeats = seatsRepository.findByTrainNoAndCoachNo(trainId, coachId);
        List<SeatDetailsResponse> response = new ArrayList<SeatDetailsResponse>();
        for (Seat seat : allSeats) {
            SeatDetailsResponse resSeat = new SeatDetailsResponse();
            resSeat.setCoachId(seat.getCoachNo());
            resSeat.setCoachType(seat.getCoachtype());
            resSeat.setSeatNumber(seat.getSeatNo());
            resSeat.setTrainID(trainId);
            response.add(resSeat);
        }
        return response;
    }

    @Override
    public String removeCoach(Long uId, Long trainId, List<Long> coachIDs) {
        List<String> response = new ArrayList<String>();
        for (Long coach : coachIDs) {
            if (!seatsRepository.existsByCoachNo(coach)) {
                response.add("Coach ID " + coach + " does not exist.");
            } else {
                List<Seat> seatPerCoach = seatsRepository.findByTrainNoAndCoachNo(trainId, coach);
                seatsRepository.deleteAll(seatPerCoach);
            }
        }
        return response.toString();
    }

}
