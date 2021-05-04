package mg.ru.cinema.network.dto;

import lombok.Getter;

import java.util.Date;
@Getter
public class Poster {
    private final long sessionId;
    private final String movieName;
    private final Date dt;

    public Poster(long sessionId, String movieName, Date dt) {
        this.sessionId = sessionId;
        this.movieName = movieName;
        this.dt = dt;
    }
}
