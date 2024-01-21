<script setup>
import {onMounted, ref} from "vue";
import {useRecipeStore} from "@/stores/RecipeStore";
import {useCourseStore} from "@/stores/CourseStore";

const recipeStore = useRecipeStore()
const courseStore = useCourseStore()
const err = false
const ingredients = [];

const recipeData = ref({
  title: '',
  description: '',
  difficulty: 1,
  preparation: 0,
  selectedCourses: [],
  ingredients: [],
});
const courseOptions = ref([])
const isCoursesLoaded = ref(false);

onMounted(async () => {
  await loadCourses();

});


const loadCourses = async () => {
  try {
    await courseStore.showCourses();
    courseOptions.value = courseStore.courses.map(course => ({
      courseId: course.courseId,
      title: course.courseTitle
    }));
  } catch (err) {
    console.error("Fehler beim Laden der Kurse:", err);
  }
};



const addRecipe = async () => {
  console.log('addRecipe function called');
  try {
    console.dir(recipeData.value.selectedCourses);
    //const selectedCourseIds = recipeData.value.selectedCourses.map(course => course.courseId);

    let courseIds = [];
    courseIds.push(recipeData.value.selectedCourses);

    const recipeDataToSend = {
      title: recipeData.value.title,
      description: recipeData.value.description,
      difficulty: recipeData.value.difficulty,
      preparation: recipeData.value.preparation,
      courseIds: courseIds,
      ingredients: recipeData.value.ingredients
    };

    await recipeStore.addRecipe(recipeDataToSend);
    console.log('recipe created:', recipeDataToSend)
    await recipeStore.showRecipes();
  } catch (err) {
    console.error('Error creating recipe:', err);
  }
}


loadCourses();


</script>

<template>
  <v-sheet elevation="4" width="80%" class="mx-auto mt-5">
<h1>Erstelle ein neues Rezept:</h1>
      <v-container>
        <v-form @submit.prevent="addRecipe">
          <v-row>
            <v-col>
              <v-text-field v-model="recipeData.title" label="Titel"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field v-model="recipeData.difficulty" label="Schwierigkeitsgrad" type="number"></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-text-field v-model="recipeData.description" label="Beschreibung"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field v-model="recipeData.preparation" label="Vorbereitungszeit (in Minuten)" type="number"></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-row>
                <!-- Eingabefeld für Zutaten -->
                <v-text-field v-model="newIngredient" label="Zutat" @keydown.enter.prevent="addIngredient"></v-text-field>
                <v-text-field v-model="newQuantity" label="Menge"></v-text-field>
                <v-text-field v-model="newUnit" label="Einheit"></v-text-field>


              </v-row>

            </v-col>

            <v-col>
              <v-select
                  v-model="recipeData.selectedCourses"
                  :items="courseOptions"
                  label="Kurs"

                  item-value="courseId"
                 ></v-select>

            </v-col>

          </v-row>
          Zutatenliste:
          <ul>
            <li v-for="(ingredient, index) in ingredients" :key="index">
              {{ ingredient.quantity }} - Menge: {{ ingredient.unit }} {{ ingredient.title }}
            </li>
          </ul>
          <div>

          </div>
          <v-btn type="submit">Rezept erstellen</v-btn>
        </v-form>
      </v-container>
  </v-sheet>


</template>

<style scoped>

</style>
