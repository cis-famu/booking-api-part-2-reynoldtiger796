package edu.famu.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetail {
    public Rooms room;
    private int adults;
    private int children;
    private double pricePerNight;
}
