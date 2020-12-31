package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    //Call the commentByImageID() method in the Repository and obtain a List of all the comments in the database
    public List<Comment> commentByImageID(Integer imageId) {
        return commentRepository.commentsByImageID(imageId);
    }

    //The method calls the addComment() method in the Repository and passes the comment to be persisted in the database
    public Comment addComment(Comment comment) {
        return commentRepository.addComment(comment);
    }
}