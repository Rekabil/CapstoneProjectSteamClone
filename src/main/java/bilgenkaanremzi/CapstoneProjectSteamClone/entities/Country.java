package bilgenkaanremzi.CapstoneProjectSteamClone.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(unique = true)
    private String name;
    private String code;

}
