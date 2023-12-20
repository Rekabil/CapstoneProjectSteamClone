package bilgenkaanremzi.CapstoneProjectSteamClone.entities;

import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@JsonIgnoreProperties({"password", "authorities", "enabled", "credentialsNonExpired", "accountNonExpired", "accountNonLocked"})
public class User implements UserDetails {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @ToString.Include
    private String username;
    @ToString.Include
    private String displayName;
    @ToString.Include
    private String email;
    @ToString.Include
    private String password;

    @ToString.Exclude
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

@ManyToOne
@JoinColumn(name = "country_id")
private Country country;

@ManyToMany(fetch = FetchType.EAGER)
private List<Game> ownedGames;


@OneToMany(fetch = FetchType.EAGER)
private List<Review> reviews;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
