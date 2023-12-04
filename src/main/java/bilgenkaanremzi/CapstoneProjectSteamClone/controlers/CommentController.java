package bilgenkaanremzi.CapstoneProjectSteamClone.controlers;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comment;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.BadRequestException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.CommentDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment saveComment(@RequestBody @Validated CommentDTO body , BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return commentService.save(body);
        }
    }

    public Page<Comment> getComments(@RequestParam(defaultValue = "0") int page ,@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "0") long comunityId, @RequestParam(defaultValue = "0") long userId) {
        return commentService.getComment(page, size, orderBy, comunityId, userId);
    }
    @GetMapping("/{id}")
    public Comment findById(@PathVariable long id) {
        return commentService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDeleteById(@PathVariable long id) {
        commentService.findAndDeleteById(id);
    }

}
