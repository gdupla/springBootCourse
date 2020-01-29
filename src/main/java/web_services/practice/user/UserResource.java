package web_services.practice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import web_services.practice.user.exceptions.UserNotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) throws UserNotFoundException{
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id - " + id);

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) throws UserNotFoundException{
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id - " + id);
    }

//    @GetMapping("/users/{id}/posts")
//    public List<Post> retrieveAllPosts(@PathVariable int id){
//        return service.findAllPosts(id);
//    }
//
//    @GetMapping("/users/{id}/posts/{post_id}")
//    public Post retrievePostDetails(@PathVariable int id, @PathVariable int post_id) throws UserNotFoundException, PostNotFoundException {
//        User user = service.findOne(id);
//        if (user == null)
//            throw new UserNotFoundException("id - " + id);
//
//        Post post = service.findOnePost(user.getId(),post_id);
//        if (post == null)
//            throw new PostNotFoundException("post_id - " + post_id);
//
//        return post;
//    }
//
//    @PostMapping("/users/{id}/posts")
//    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
//        Post savedPost = service.createPost(id, post);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{post_id}").buildAndExpand(savedPost.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
//    }
}
