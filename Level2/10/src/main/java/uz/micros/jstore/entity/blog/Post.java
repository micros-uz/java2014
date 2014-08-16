package uz.micros.jstore.entity.blog;

import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private String subject;
    private String text;
    private Date date;
    private String author;
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
