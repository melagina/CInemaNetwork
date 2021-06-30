package mg.ru.cinema.network.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String movieName;
    @Column
    private Date time;

    @OneToMany(mappedBy = "session")
    private List<Seat> seats;

    public Session(Long id, String movieName, Date time, List<Seat> seats) {
        this.id = id;
        this.movieName = movieName;
        this.time = time;
        this.seats = seats;
    }
}
