package edu.famu.booking.controller;

import edu.famu.booking.model.Reservation;
import edu.famu.booking.service.ReservationService;
import edu.famu.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        String reservationId = reservationService.createReservation(reservation);
        return ResponseEntity.ok(reservationId);
    }
}

