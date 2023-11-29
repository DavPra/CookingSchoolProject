package Cooking.School.Project.cookingSchool.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "recipe_tags")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeTag {

        @Id
        @GeneratedValue(generator = "tagSequence")
        @GenericGenerator(
                name = "tagSequence",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tag_sequence"),
                        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
                }

        )

        private Long tagId;


        @Setter
        private String title;

        @Setter
        @ManyToMany(mappedBy = "recipeTags")
        private Set<Recipe> recipes;


}
