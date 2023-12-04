package bilgenkaanremzi.CapstoneProjectSteamClone.repositories;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByComunity(String communityId,
                                 Pageable pageable);
    Page<Comment> findByUser(String userId, Pageable pageable);
}
