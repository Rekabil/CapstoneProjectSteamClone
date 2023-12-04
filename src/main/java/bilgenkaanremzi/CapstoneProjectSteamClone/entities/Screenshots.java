package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Screenshot")
@Getter
@Setter
public class Screenshots extends Comunity {
    private String picture;

}
