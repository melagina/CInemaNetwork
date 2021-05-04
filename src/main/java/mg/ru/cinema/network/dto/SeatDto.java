package mg.ru.cinema.network.dto;

import lombok.Getter;

@Getter
public class SeatDto {
    private final int row;
    private final int seatNumber;
    private final boolean vacant;

    public SeatDto(int row, int seatNumber, boolean vacant) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.vacant = vacant;
    }
}
