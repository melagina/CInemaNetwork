package mg.ru.cinema.network.service;

import mg.ru.cinema.network.dto.Poster;
import mg.ru.cinema.network.dto.SeatDto;
import mg.ru.cinema.network.dto.Ticket;
import mg.ru.cinema.network.exception.SeatBookingException;

import java.util.Set;
/*
* Сервис для обработки запросов посетителей кинотеатра
* */
public interface CinemaClientService {
    Set<Poster> showMovies();
    Ticket buyTicket(Long sessionId, int row, int seatNumber) throws SeatBookingException;
    void returnTicket(Ticket ticket) throws SeatBookingException;
    Set<SeatDto> showAvaliableSeats(Long sessionId);

}
