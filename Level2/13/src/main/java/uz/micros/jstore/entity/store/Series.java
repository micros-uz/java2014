package uz.micros.jstore.entity.store;

import uz.micros.jstore.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Series extends BaseEntity{
    @Column(unique = true, nullable = false, columnDefinition = "varchar(25)")
    private String title;

    @Column(unique = true, columnDefinition = "varchar(250)", name="description")
    private String desc;

    @Column(insertable = false, updatable = false)
    private int genre_id;

    @ManyToOne
    @JoinColumn(name="genre_id", nullable = false)
    private Genre genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
