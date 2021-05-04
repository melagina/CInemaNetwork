package ru.cinema.network.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cinema.network.dto.Ticket;
import ru.cinema.network.exception.SeatBookingException;
import ru.cinema.network.model.Seat;
import ru.cinema.network.repository.MovieSessionRepo;
import ru.cinema.network.service.CinemaClientSevice;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CinemaClientServiceImpl implements CinemaClientSevice {

    @Autowired private MovieSessionRepo movieSessionRepo;

    @Override
    public Ticket buyTicket(String movieName, Date dt, int row, int seatNumber) throws SeatBookingException {
        Seat seat = movieSessionRepo.findMovieSessionByNameAndDate(movieName, dt).getSeats()
                .stream()
                .filter(s -> s.getSeatNumber() == seatNumber && s.getRow() == row && s.isVacant())
                .findAny()
                .orElseThrow(SeatBookingException::new);
        seat.setVacant(false);
        return new Ticket(seat.getRow(), seat.getSeatNumber(), movieName, dt);

    }

    @Override
    public void returnTicket(Ticket ticket) throws SeatBookingException {
        Seat seat = movieSessionRepo.findMovieSessionByNameAndDate(ticket.getMovieName(), ticket.getDt())
                .getSeats()
                .stream()
                .filter(s -> s.getSeatNumber() == ticket.getSeatNumber()
                        && s.getRow() == ticket.getRow()
                        && !s.isVacant())
                .findFirst()
                .orElseThrow(SeatBookingException::new);
        seat.setVacant(true);
    }

    @Override
    public Set<Seat> showAvaliableSeats(String movieName, Date dt) {
        return movieSessionRepo.findMovieSessionByNameAndDate(movieName, dt).getSeats()
                .stream()
                .filter(seat -> seat.isVacant())
                .collect(Collectors.toSet());
    }
}
