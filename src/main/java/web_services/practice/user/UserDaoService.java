package web_services.practice.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();

    private static int usersCount = 3;
    private static int postsCount = 0;

    static {
        users.add(new User(1, "Guillermo", new Date()));
        users.add(new User(2, "Elvira", new Date()));
        users.add(new User(3, "Alejandro", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save (User user){
        ++usersCount;
        if (user.getId()==null)
            user.setId(usersCount);
        users.add(user);
        return user;
    }

    public User findOne (int id){
        User result = null;
        for (User user: users){
            if(user.getId() == id)
                result = user;
        }
        return result;
    }

    public User deleteById (int id){
        Iterator<User> iterator = users.iterator();
        User user_to_delete = null;
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();
                user_to_delete = user;
            }
        }

        return user_to_delete;
    }

    public List<Post> findAllPosts(int id) {
        return findOne(id).getPosts();
    }

    public Post createPost(int userId, Post post) {
        ++postsCount;
        if (post.getId()==null)
            post.setId(postsCount);
        User user = findOne(userId);

        post.setAuthor(user);
        post.setPostDate(new Date());

        user.getPosts().add(post);

        return post;
    }

    public Post findOnePost(int idUser, int idPost) {
        Post result = null;
        for (Post post: findAllPosts(idUser)){
            if(post.getId() == idPost)
                result = post;
        }

        return result;
    }
}
