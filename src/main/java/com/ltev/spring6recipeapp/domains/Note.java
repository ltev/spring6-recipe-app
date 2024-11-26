package com.ltev.spring6recipeapp.domains;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipeNote;

    @OneToOne(mappedBy = "note")    // Recipe -> note
    private Recipe recipe;

    public Note(String recipeNote) {
        this.recipeNote = recipeNote;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", recipeNote='" + recipeNote + '\'' +
                ", recipeId=" + (recipe != null ? recipe.getId() : null) +
                '}';
    }
}
