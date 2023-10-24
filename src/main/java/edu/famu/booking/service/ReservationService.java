package edu.famu.booking.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.booking.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ReservationService {
    private Firestore firestore;

    public ReservationService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public String createReservation(Reservation reservation) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("Reservations").document();
        docRef.set(reservation); // This will create a new document with an automatically generated ID.

        String reservationId = docRef.getId();

        return reservationId;
    }
}