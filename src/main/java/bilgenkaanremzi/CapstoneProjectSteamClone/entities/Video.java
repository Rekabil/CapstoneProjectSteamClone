package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Video")
@Getter
@Setter
public class Video extends Comunity {
    private String video;
}
