package web_services.practice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String content;
    private Date postDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User author;

    public Post () {}

    public Post(Integer id, String title,  String content, Date postDate, User author) {
        super();
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Override
    public String toString(){
        return String.format("Post id=%s, author=%s, title=%s, content=%s, timestamp=%s", id, author, title, content, postDate.toString());
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date timestamp) {
        this.postDate = postDate;
    }
}
