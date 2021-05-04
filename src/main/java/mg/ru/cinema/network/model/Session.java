package mg.ru.cinema.network.model;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class Session {
    private final Long id;
    private final String movieName;
    private final Date time;

    private final Set<Seat> seats;

    public Session(Long id, String movieName, Date time, Set<Seat> seats) {
        this.id = id;
        this.movieName = movieName;
        this.time = time;
        this.seats = seats;
    }
}
