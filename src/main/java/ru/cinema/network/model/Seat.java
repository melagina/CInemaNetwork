package ru.cinema.network.model;

import lombok.Getter;
import ru.cinema.network.exception.SeatBookingException;

@Getter
public class Seat {

    private final int row;
    private final int seatNumber;
    private boolean vacant;

    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.vacant = true;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }
}
