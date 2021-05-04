package mg.ru.cinema.network.dto;

import lombok.Getter;
import java.util.Date;

@Getter
public class Ticket {
    private final int row;
    private final int seatNumber;
    private final String movieName;
    private final Date dt;

    public Ticket(int row, int seatNumber, String movieName, Date dt) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.movieName = movieName;
        this.dt = dt;
    }
}
