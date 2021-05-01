package ru.cinema.network.repository;

import org.springframework.stereotype.Repository;
import ru.cinema.network.model.MovieSession;
import ru.cinema.network.model.Seat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MovieSessionRepo {
    //TODO: подключить БД

    private Set<MovieSession> sessions;
    public MovieSessionRepo() throws ParseException {
        sessions = createSessionSet();
    }

    public MovieSession findMovieSessionByNameAndDate(String name, Date dt) {
        return sessions.stream()
                .filter(session -> session.getMovieName().equals(name))
                .filter(session -> session.getTime().equals(dt))
                .findAny()
                .orElseThrow(() -> new RuntimeException("No such movie!"));
    }

    private Set<MovieSession> createSessionSet() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        MovieSession ms1 = new MovieSession("Lord of the Rings",
                formatter.parse("01.01.2021 10:00:00"),
                new HashSet<>() {{
                    add(new Seat(1,2));
                    add(new Seat(1,1));
                }}
        );
        MovieSession ms2 = new MovieSession("Home Alone",
                formatter.parse("02.01.2021 10:00:00"),
                new HashSet<>() {{
                    add(new Seat(1,2));
                    add(new Seat(1,1));
                }}
        );
        MovieSession ms3 = new MovieSession("Game of thrones",
                formatter.parse("03.01.2021 10:00:00"),
                new HashSet<>() {{
                    add(new Seat(1,2));
                    add(new Seat(1,1));
                }}
        );
        return new HashSet<>(){{
            add(ms1);add(ms2);add(ms3);
        }};
    }
}
