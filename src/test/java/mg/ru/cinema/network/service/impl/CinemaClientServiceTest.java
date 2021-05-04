package mg.ru.cinema.network.service.impl;

import mg.ru.cinema.network.dto.Ticket;
import mg.ru.cinema.network.exception.SeatBookingException;
import mg.ru.cinema.network.service.CinemaClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CinemaClientServiceTest {

    @Autowired private CinemaClientService clientService;

    @Test
    @DisplayName("Test should show all movies")
    public void showAllMoviesTest(){
        Assertions.assertFalse(clientService.showMovies().isEmpty());
    }

    @Test
    @DisplayName("Test should show all avaliable seats")
    public void showAllSeatsTest(){
        Assertions.assertFalse(clientService.showAvaliableSeats(1L).isEmpty());
    }

    @Test
    @DisplayName("Test should let buy ticket only for one time")
    public void buyTicketTest(){
        Assertions.assertNotNull(clientService.buyTicket(1L,1,1));
        Assertions.assertThrows(SeatBookingException.class, () -> {clientService.buyTicket(1L,1,1);});
    }

    @Test
    @DisplayName("Test should let return ticket only for one time")
    public void retutnTicketTest(){
        Ticket ticket = clientService.buyTicket(1L,1,1);
        Assertions.assertDoesNotThrow(() -> clientService.returnTicket(ticket));
        Assertions.assertTrue(
                clientService.showAvaliableSeats(1L)
                        .stream()
                .anyMatch(s ->
                        s.getSeatNumber() == ticket.getSeatNumber()
                        && s.getRow() == ticket.getRow()));
        Assertions.assertThrows(SeatBookingException.class, () -> {clientService.returnTicket(ticket);});
    }

}