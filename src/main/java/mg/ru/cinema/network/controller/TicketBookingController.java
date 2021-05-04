package mg.ru.cinema.network.controller;

import mg.ru.cinema.network.dto.Poster;
import mg.ru.cinema.network.dto.SeatDto;
import mg.ru.cinema.network.dto.Ticket;
import mg.ru.cinema.network.service.CinemaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/movies")
public class TicketBookingController {
    @Autowired private CinemaClientService clientService;

    @GetMapping
    public Set<Poster> getAllMovies() {
        return clientService.showMovies();
    }

    @GetMapping("{sessionId}")
    public Set<SeatDto> getAllAvaliableSeats(@PathVariable Long sessionId){
        return clientService.showAvaliableSeats(sessionId);
    }

    @PostMapping("{sessionId}")
    public Ticket buyTicker(@PathVariable Long sessionId,
                            @RequestBody SeatDto seat ) {
        return clientService.buyTicket(sessionId, seat.getRow(), seat.getSeatNumber());
    }

    @PostMapping
    public void returnTicker(@RequestBody Ticket ticket) {
        clientService.returnTicket(ticket);
    }
}
