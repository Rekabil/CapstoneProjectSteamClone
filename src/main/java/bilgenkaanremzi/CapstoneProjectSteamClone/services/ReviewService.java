package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Review;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.NotFoundException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.ReviewDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    GameService gameService;

    public Review save(ReviewDTO body, long id) {
        Game game = gameService.findById(body.gameId());
        Review newReview = new Review();
        newReview.setContent(body.content());
        newReview.setGame(game);
        newReview.setRating(body.rating());
        newReview.setContent(body.content());
        return  reviewRepository.save(newReview);
    }
    public Review findById(long id) {
        return reviewRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Page<Review> getReview(int page,int size,String orderBy , int ratingLess,int ratingGreater , long gameId) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        List<Review> reviews = reviewRepository.findAll();
        if (ratingGreater != 0) {
            reviews.stream().filter(f-> f.getRating() < ratingGreater).collect(Collectors.toList());
        }
        if (ratingLess != 0) {
            reviews.stream().filter(f -> f.getRating() > ratingLess).collect(Collectors.toList());
        }
        if (gameId != 0 ) {
            reviews.stream().filter(f -> f.getGame().getId() == gameId).collect(Collectors.toList());
        }

        return new PageImpl<>(reviews,pageable,reviews.size());
     }

     public void findAndDeleteById(long id) {
        Review found = this.findById(id);
        reviewRepository.delete(found);
     }
}
