package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("News")
@Getter
@Setter
public class News extends Comunity {
    private String picture;
    private String video;
    private String content;
}
