/*
 */
package com.example.demo.controller;

import com.example.demo.component.Coach;
import com.example.demo.model.SeatDetailsResponse;
import com.example.demo.service.CoachService;
import com.example.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ianur
 */
@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CoachService coachService;

// add additional coaches to the train
    @PostMapping(value = "/coach/addCoach")
    public ResponseEntity<Object> addCoachToTrain(
            @RequestParam("userId") Long uId,
            @RequestParam("trainId") Long trainId,
            @RequestParam("coachType") Coach coachType,
            @RequestParam("coachIDList") List<Long> coachIDs) {
        try {
            if (!userService.isAdmin(uId)) {
                return new ResponseEntity<>("You do not have access to this section.", HttpStatus.BAD_REQUEST);
            }

            String response = coachService.addCoach(uId, trainId, coachType, coachIDs);
            if (response == "") {
                return new ResponseEntity<>("Coach Added Successfully.", HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Our page is down. Fix in progress.", HttpStatus.BAD_REQUEST);
        }

    }

    // view all seats in a coach/ train
    @GetMapping(value = "/seats/allSeatsDeatials")
    public ResponseEntity<Object> viewSeatDetails(
            @RequestParam("userId") Long uId,
            @RequestParam("trainId") Long trainId,
            @RequestParam("coachId") Long coachId) {
        try {
            if (!userService.isAdmin(uId)) {
                return new ResponseEntity<>("You do not have access to this section.", HttpStatus.BAD_REQUEST);
            }
            if (!coachService.validTrainID(trainId)) {
                return new ResponseEntity<>("Invalid Train ID", HttpStatus.BAD_REQUEST);
            }
            List<SeatDetailsResponse> response = new ArrayList<SeatDetailsResponse>();
            if (coachId != null) {
                response = coachService.viewSeatsByCoach(trainId, coachId);
            } else {
                response = coachService.viewSeatsByTrain(trainId);
            }

            // TODO
            if (response.size() > 0) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>("No data found", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Our page is down. Fix in progress.", HttpStatus.BAD_REQUEST);
        }

    }

    // remove coaches from the train
    @PostMapping(value = "/coach/removeCoach")
    public ResponseEntity<Object> removeCoachFromTrain(
            @RequestParam("userId") Long uId,
            @RequestParam("trainId") Long trainId,
            @RequestParam("coachIDList") List<Long> coachIDs) {
        try {
            if (!userService.isAdmin(uId)) {
                return new ResponseEntity<>("You do not have access to this section.", HttpStatus.BAD_REQUEST);
            }
            if (!coachService.validTrainID(trainId)) {
                return new ResponseEntity<>("Invalid Train ID", HttpStatus.BAD_REQUEST);
            }
            String response = coachService.removeCoach(uId, trainId, coachIDs);
            if (response == "") {
                return new ResponseEntity<>("Coach deleted Successfully.", HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Our page is down. Fix in progress.", HttpStatus.BAD_REQUEST);
        }

    }
}
