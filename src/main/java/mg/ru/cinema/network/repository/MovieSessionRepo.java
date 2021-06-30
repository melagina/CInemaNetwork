package mg.ru.cinema.network.repository;

import org.springframework.stereotype.Repository;
import mg.ru.cinema.network.model.Session;
import mg.ru.cinema.network.model.Seat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MovieSessionRepo {
    //TODO: подключить БД

    private Set<Session> sessions;
    public MovieSessionRepo() throws ParseException {
        sessions = createSessionSet();
    }

    public Set<Session> findAllMoviesSession(){
        return sessions;
    }

    public Optional<Session> findSessionById(Long sessionId) {
        return sessions.stream()
                .filter(session -> session.getId().equals(sessionId))
                .findAny()
                ;
//                .orElseThrow(() -> new RuntimeException("No such movie!"));
    }

    public Session findMovieSessionByNameAndDate(String name, Date dt) {
        return sessions.stream()
                .filter(session -> session.getMovieName().equals(name))
                .filter(session -> session.getTime().equals(dt))
                .findAny()
                .orElseThrow(() -> new RuntimeException("No such movie!"));
    }

    private Set<Session> createSessionSet() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        Session ms1 = new Session(1L,
                "Lord of the Rings",
                formatter.parse("01.01.2021 10:00:00"),
                new ArrayList<>() {{
                    add(new Seat(1,2));
                    add(new Seat(1,1));
                }}
        );
        Session ms2 = new Session(2L,
                "Home Alone",
                formatter.parse("02.01.2021 10:00:00"),
                new ArrayList<>() {{
                    add(new Seat(1,2));
                    add(new Seat(1,1));
                }}
        );
        Session ms3 = new Session(3L,
                "Game of thrones",
                formatter.parse("03.01.2021 10:00:00"),
                new ArrayList<>() {{
                    add(new Seat(1,2));
                    add(new Seat(1,1));
                }}
        );
        return new HashSet<>(){{
            add(ms1);add(ms2);add(ms3);
        }};
    }
}
