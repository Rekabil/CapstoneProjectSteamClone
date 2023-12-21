package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "comunity_id")
    private Comunity comunity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}
