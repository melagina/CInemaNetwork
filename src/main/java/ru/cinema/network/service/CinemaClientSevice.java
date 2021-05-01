package ru.cinema.network.service;

import ru.cinema.network.dto.Ticket;
import ru.cinema.network.exception.SeatBookingException;
import ru.cinema.network.model.Seat;

import java.util.Date;
import java.util.Set;

public interface CinemaClientSevice {

    Ticket buyTicket(String movieName, Date dt, int row, int seatNumber) throws SeatBookingException;
    void returnTicket(Ticket ticket) throws SeatBookingException;
    Set<Seat> showAvaliableSeats(String movieName, Date dt);

}
