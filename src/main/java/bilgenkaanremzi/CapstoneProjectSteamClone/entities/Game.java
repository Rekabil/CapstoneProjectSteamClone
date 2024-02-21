package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "games")
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private List<String> preview;
    private String description;
    private String details;
    @Enumerated(EnumType.STRING)
private List<Category> categories;
private LocalDate releaseDate;
private int year;
private double price;
private double salePrice;
private String developer;
private String publisher;

@ManyToMany
private List<User> purchesedOwners;

    @OneToMany
    private Set<Review> reviews;
    @OneToMany
private Set<Comunity> comunities;
}
