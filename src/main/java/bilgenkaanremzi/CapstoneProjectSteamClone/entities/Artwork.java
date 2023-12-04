package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Artwork")
@Getter
@Setter
public class Artwork extends Comunity {
    private String picture;
}
