package bilgenkaanremzi.CapstoneProjectSteamClone.repositories;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    public Page<Game> findByCategoriesContaining(Category category, Pageable pageable);

    public Page<Game> findByTitleContaining(String title, Pageable pageable);

    public Page<Game> findByYear(int year, Pageable pageable);

    public Page<Game> findByPriceGreaterThan(double price, Pageable pageable) ;

    public Page<Game> findByPriceLessThan(double price, Pageable pageable) ;
}
