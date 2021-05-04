package mg.ru.cinema.network.service.impl;

import mg.ru.cinema.network.dto.Poster;
import mg.ru.cinema.network.dto.SeatDto;
import mg.ru.cinema.network.dto.Ticket;
import mg.ru.cinema.network.exception.SeatBookingException;
import mg.ru.cinema.network.model.Seat;
import mg.ru.cinema.network.model.Session;
import mg.ru.cinema.network.repository.MovieSessionRepo;
import mg.ru.cinema.network.service.CinemaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CinemaClientServiceImpl implements CinemaClientService {

    @Autowired private MovieSessionRepo movieSessionRepo;

    @Override
    public Ticket buyTicket(Long sessionId, int row, int seatNumber) throws SeatBookingException {
        Session session = movieSessionRepo.findSessionById(sessionId)
                .orElseThrow(() -> new RuntimeException("No such movie!"));;
        Seat seat = session.getSeats()
                .stream()
                .filter(s -> s.getSeatNumber() == seatNumber && s.getRow() == row && s.isVacant())
                .findAny()
                .orElseThrow(() -> new SeatBookingException("Seat is not vacant!"));
        seat.setVacant(false);
        return new Ticket(seat.getRow(), seat.getSeatNumber(), session.getMovieName(), session.getTime());
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
                .orElseThrow(() -> new SeatBookingException("Seat is vacant!"));
        seat.setVacant(true);
    }

    @Override
    public Set<SeatDto> showAvaliableSeats(Long sessionId) {
        return movieSessionRepo.findSessionById(sessionId)
                .orElseThrow(() -> new RuntimeException("No movie session woth Id" + sessionId))
                .getSeats()
                .stream()
                .filter(seat -> seat.isVacant())
                .map(s -> new SeatDto(s.getRow(), s.getSeatNumber(), s.isVacant()) )
                .collect(Collectors.toSet());
    }

    public Set<Poster> showMovies() {
        return movieSessionRepo.findAllMoviesSession().stream()
                .map(s -> new Poster(s.getId(), s.getMovieName(), s.getTime()))
                .collect(Collectors.toSet());
    }
}
