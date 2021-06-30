package mg.ru.cinema.network.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Getter @Setter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue
    private int row;
    @Column
    private int seatNumber;
    @Column
    private boolean vacant;
    @ManyToOne
    private Session session;

    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.vacant = true;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }
}
