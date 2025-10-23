package DTO;

import javax.validation.constraints.NotBlank;

public class CategoryDTO {

    private Long idCategory;

    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    private String name;

    private String description;

    private Integer filmCount;

    // Constructeurs
    public CategoryDTO() {}

    // Getters et Setters
    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFilmCount() {
        return filmCount;
    }

    public void setFilmCount(Integer filmCount) {
        this.filmCount = filmCount;
    }
}