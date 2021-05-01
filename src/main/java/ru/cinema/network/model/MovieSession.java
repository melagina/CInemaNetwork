package ru.cinema.network.model;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class MovieSession {

    private final String movieName;
    private final Date time;

    private final Set<Seat> seats;

    public MovieSession(String movieName, Date time, Set<Seat> seats) {
        this.movieName = movieName;
        this.time = time;
        this.seats = seats;
    }
}
