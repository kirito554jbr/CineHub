package DTO;

import javax.validation.constraints.*;

public class FilmDTO {

    private Long idFilm;

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotNull(message = "L'année de sortie est obligatoire")
    @Min(value = 1888)
    @Max(value = 2025)
    private Integer releaseYear;

    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1)
    private Integer duration;

    private String synopsis;

    @NotNull(message = "La note est obligatoire")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double rating;

    @NotNull(message = "Le réalisateur est obligatoire")
    private Long directorId;

    private String directorName;

    @NotNull(message = "La catégorie est obligatoire")
    private Long categoryId;

    private String categoryName;

    // Constructeurs
    public FilmDTO() {}

    // Getters et Setters
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}