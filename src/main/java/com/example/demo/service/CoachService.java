/*
 */
package com.example.demo.service;

import com.example.demo.component.Coach;
import com.example.demo.model.SeatDetailsResponse;
import java.util.List;

/**
 *
 * @author ianur
 */
public interface CoachService {

    public boolean validTrainID(Long trainId);

    public String addCoach(Long adminId, Long trainId, Coach coachType, List<Long> coachIDs);

    public List<SeatDetailsResponse> viewSeatsByTrain(Long trainId);

    public List<SeatDetailsResponse> viewSeatsByCoach(Long trainId, Long coachId);

    public String removeCoach(Long uId, Long trainId, List<Long> coachIDs);
}
