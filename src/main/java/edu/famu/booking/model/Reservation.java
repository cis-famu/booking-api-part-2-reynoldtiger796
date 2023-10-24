package edu.famu.booking.model;



import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.text.ParseException;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Users customer;
    private Hotels hotel;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private double total;
    private ArrayList<ReservationDetail> rooms;


    public void setCheckIn(String checkIn) throws ParseException {
        this.checkIn = Timestamp.fromProto(Timestamps.parse(checkIn));
    }

    public void setCheckOut(String checkOut) throws ParseException {
        this.checkOut = Timestamp.fromProto(Timestamps.parse(checkOut));
    }
}
