package edu.famu.booking.service;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.booking.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class ReservationService {
    private final Firestore firestore;

    public ReservationService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public String createReservation(Reservation reservation) {
        String reservationId = UUID.randomUUID().toString();

        Map<String, Object> reservationData = new HashMap<>();
        reservationData.put("customerId", reservation.getCustomer().getUserID());
        reservationData.put("hotelId", reservation.getHotel().getHotelID());
        reservationData.put("checkIn", reservation.getCheckIn().toString());
        reservationData.put("checkOut", reservation.getCheckOut().toString());
        reservationData.put("rooms", reservation.getRooms());

        DocumentReference reservationDocRef = firestore.collection("Reservations").document(reservationId);
        reservationDocRef.set(reservationData);

        return reservationId;
    }
}
