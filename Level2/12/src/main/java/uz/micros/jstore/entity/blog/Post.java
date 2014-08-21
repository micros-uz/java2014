package uz.micros.jstore.entity.blog;

import uz.micros.jstore.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    @Column(nullable = false, columnDefinition = "varchar(120)")
    private String subject;
    @Column(nullable = false, columnDefinition = "varchar(4096)")
    private String text;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String author;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public String getShortText(){
        return text != null && text.length() > 0
                ? (text.length() > 100 ? text.substring(0, 99) + "..." : text)
                : "";
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
