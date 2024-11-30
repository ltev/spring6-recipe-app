package com.ltev.spring6recipeapp.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @OneToOne(mappedBy = "note")    // Recipe -> note
    private Recipe recipe;

    public Note(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", recipeNote='" + description + '\'' +
                ", hasRecipe='" + (recipe != null) + '\'' +
                ", recipeId=" + (recipe != null ? recipe.getId() : null) +
                '}';
    }

    /**
     * This method will be called automatically from:
     * Recipe -> setNote()
     */
    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
