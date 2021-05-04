package mg.ru.cinema.network.exception;

public class SeatBookingException extends RuntimeException{
    public SeatBookingException() {

    }
    public SeatBookingException(String message) {
        super(message);
    }
}
