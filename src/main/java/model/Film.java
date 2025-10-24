package model;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idFilm;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int releaseYear;
    @Column(nullable = false)
    private int duration; // duration in minutes
    @Column(nullable = false, length = 1000)
    private String synopsis;
    @Column(nullable = false)
    private Double rating;// rating out of 10
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idDirector")
    private Director director;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCategory")
    private Category category;

    public Film() {}

    public Long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm=" + idFilm +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", duration=" + duration +
                ", synopsis='" + synopsis + '\'' +
                ", rating=" + rating +
                ", director=" + director +
                ", category=" + category +
                '}';
    }
}
