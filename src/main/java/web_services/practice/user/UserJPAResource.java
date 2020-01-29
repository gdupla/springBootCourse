package web_services.practice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import web_services.practice.user.exceptions.PostNotFoundException;
import web_services.practice.user.exceptions.UserNotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new UserNotFoundException(("id - " + id));

        return userOptional.get();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) throws UserNotFoundException{
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new UserNotFoundException(("id - " + id));

        return userOptional.get().getPosts();
    }

    @GetMapping("/jpa/users/{id}/posts/{post_id}")
    public Post retrievePostDetails(@PathVariable int id, @PathVariable int post_id) throws UserNotFoundException, PostNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new UserNotFoundException(("id - " + id));

        Optional<Post> postOptional = postRepository.findById(post_id);
        if(!postOptional.isPresent())
            throw new PostNotFoundException(("post_id - " + post_id));

        if(postOptional.get().getAuthor().getId() != id)
            throw new PostNotFoundException(("id - " + id + " / post_id - " + post_id));

        return postOptional.get();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new UserNotFoundException(("id - " + id));

        post.setAuthor(userOptional.get());

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{post_id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
