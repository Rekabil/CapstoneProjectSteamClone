package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Workshop")
@Getter
@Setter
public class Workshop extends Comunity{
    private String content;
}
