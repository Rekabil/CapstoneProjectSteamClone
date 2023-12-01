package bilgenkaanremzi.CapstoneProjectSteamClone.controlers;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Review;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.BadRequestException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.ReviewDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Review save(@RequestBody @Validated ReviewDTO body , BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return reviewService.save(body);
        }
    }
    @GetMapping("")
    public Page<Review> getReviewsByFiltre(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@RequestParam(defaultValue = "id") String orderBy ,@RequestParam(defaultValue = "0") int ratingLess,@RequestParam(defaultValue = "0") int ratingGreater ,@RequestParam(defaultValue = "0") long gameId) {
        return reviewService.getReview(page, size, orderBy, ratingLess, ratingGreater, gameId);
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable long id) {
        return reviewService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDeleteById(@PathVariable long id) {
        reviewService.findAndDeleteById(id);
    }

}
