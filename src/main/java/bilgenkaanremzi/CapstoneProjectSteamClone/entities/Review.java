package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
private int rating;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
