package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "comunity")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_of_content")
@Getter
@Setter
public abstract class Comunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany
    private Set<Comment> comments;

}
