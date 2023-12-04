package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("Guide")
@Getter
@Setter
public class Guide extends Comunity{
    private int rating;
    private String content;
    private List<String> category;
}
