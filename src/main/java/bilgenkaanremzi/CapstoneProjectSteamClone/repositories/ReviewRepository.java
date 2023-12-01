package bilgenkaanremzi.CapstoneProjectSteamClone.repositories;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByGame(Game game);

    Page<Review> findByRatingGreaterThan(int rating, Pageable pageable);

    Page<Review> findByRatingLessThan(int rating,Pageable pageable);

}
