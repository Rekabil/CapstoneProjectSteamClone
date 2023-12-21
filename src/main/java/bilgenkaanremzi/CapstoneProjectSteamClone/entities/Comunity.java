package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import bilgenkaanremzi.CapstoneProjectSteamClone.enums.ComunityCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "comunity")
@Getter
@Setter
public class Comunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;
    private String picture;
    private String video;
    @Enumerated(EnumType.STRING)
    private ComunityCategory category;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany
    private Set<Comment> comments;

}
