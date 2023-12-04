package bilgenkaanremzi.CapstoneProjectSteamClone.repositories;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunityRepository extends JpaRepository<Comunity, Long> {
}
