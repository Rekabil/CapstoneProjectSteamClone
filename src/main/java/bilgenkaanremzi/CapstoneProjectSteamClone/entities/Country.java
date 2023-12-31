package bilgenkaanremzi.CapstoneProjectSteamClone.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

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

    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    private Set<User> users;
}
