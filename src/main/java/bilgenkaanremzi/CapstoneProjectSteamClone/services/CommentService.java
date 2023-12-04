package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comment;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.NotFoundException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.CommentDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.CommentRepository;
import com.sun.java.accessibility.util.EventID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ComunityService comunityService;
    public Comment save(CommentDTO body) {
        Comment newComment = new Comment();
        newComment.setComunity(comunityService.findById(body.comunityId()));
        newComment.setContent(body.content());
        return commentRepository.save(newComment);
    }

    public Comment findById(long id) {
        return  commentRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Comment> getComment(int page,int size,String orderBy,long comunityId,long userId) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        List<Comment> comments = commentRepository.findAll();
        if (comunityId != 0) {
            comments.stream().filter(f-> f.getComunity().getId() == comunityId).collect(Collectors.toList());
        }
        if (userId != 0) {
            comments.stream().filter(f-> f.getUser().getId() == userId).collect(Collectors.toList());
        }
        return new PageImpl<>(comments,pageable,comments.size());
    }

    public void findAndDeleteById(long id) {
        Comment found = this.findById(id);
        commentRepository.delete(found);
    }
}
