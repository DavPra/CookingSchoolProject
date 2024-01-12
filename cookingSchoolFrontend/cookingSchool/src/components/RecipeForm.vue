<script setup>
import {ref} from "vue";
import {useRecipeStore} from "@/stores/RecipeStore";
import {useCourseStore} from "@/stores/CourseStore";

const recipeStore = useRecipeStore()
const courseStore = useCourseStore()
const err = false


const recipeData = ref({
  title: '',
  description: '',
  difficulty: 1,
  preparation: 0,
  courseIds: [],
  ingredients: [],
});

const courses = ref([])

const showCourses = async () => {
  try {
    console.log('load Courses', recipeStore.courseStore.courses);
    await recipeStore.getCourseIds();
  } catch (err) {
    // Handle error
    console.error('Error loading courses:', err);
  }
}

    const ingredients = [
      // Hier die verfügbaren Zutaten einfügen
    ];

const addRecipe = async () => {
  console.log('addRecipe function called');
  try {
    await recipeStore.addRecipe(recipeData.value);
    console.log('recipe created:', recipeData.value);
    await recipeStore.showRecipes();
  } catch (err) {
    // Handle error
    console.error('Error creating recipe:', err);
  }
}

showCourses();

</script>

<template>
  <div >
    <v-container>
      <v-form @submit.prevent="addRecipe">
        <v-row>
          <v-col>
            <v-text-field v-model="recipeData.title" label="Titel"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field v-model="recipeData.description" label="Beschreibung"></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <v-text-field v-model="recipeData.difficulty" label="Schwierigkeitsgrad" type="number"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field v-model="recipeData.preparation" label="Vorbereitungszeit (in Minuten)" type="number"></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <v-select
                v-model="recipeData.courseIds"
                :items="courses"
                label="Kurse"
                multiple
            ></v-select>
          </v-col>
          <v-col>
            <v-select
                v-model="recipeData.ingredients"
                :items="ingredients"
                label="Zutaten"
                multiple
            ></v-select>
          </v-col>
        </v-row>

        <v-btn type="submit">Rezept erstellen</v-btn>
      </v-form>
    </v-container>

  </div>
</template>

<style scoped>

</style>