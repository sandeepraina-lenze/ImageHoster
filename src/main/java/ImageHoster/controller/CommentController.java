package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives all the details of the comment to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    //After you get the comment entered by user, set the user of the comment by getting the logged in user from the Http Session
    //Set the date on which the comment is posted
    //Set the image for which the comment was entered
    //After storing the comment, this method redirects to the '/image/{imageId}/{imageTitle}' displaying the image with the all the comments provided

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String comment, Comment newComment, HttpSession session) throws IOException {

        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);

        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setCreatedDate(new Date());
        newComment.setText(comment);

        commentService.addComment(newComment);
        return "redirect:/images/" + imageId + "/" + image.getTitle();
    }
}