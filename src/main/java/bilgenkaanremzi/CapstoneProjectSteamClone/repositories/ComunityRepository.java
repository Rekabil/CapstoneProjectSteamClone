package bilgenkaanremzi.CapstoneProjectSteamClone.repositories;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comunity;
import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunityRepository extends JpaRepository<Comunity, Long> {
    Page<Comunity> findByGameId(long gameId,
                              Pageable pageable);
}
