<script setup>
import {onMounted, ref} from "vue";
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
const isCoursesLoaded = ref(false);

onMounted(async () => {
  await loadCourses();
});


const loadCourses = async () => {
  try {
    console.log('load Courses', recipeStore.courseStore.courses);
    courses.value = await recipeStore.getCourseIds();
    isCoursesLoaded.value = true;
  } catch (err) {
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

loadCourses();

</script>

<template>
  Erstelle ein neues Rezept:

  <div class="d-flex flex-row flex-wrap w-75 elevation-2 mx-auto ">
    <v-container >
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
               <!--<v-select
                   v-model="recipeData.ingredients"
                   :items="ingredients"
                   label="Zutaten"
                   multiple
               ></v-select> -->

               <!-- Eingabefeld für Zutaten -->

               <v-text-field
                   v-model="newIngredient"
                   label="Zutat"
                   @keydown.enter.prevent="addIngredient"
               ></v-text-field>
               <v-text-field v-model="newQuantity" label="Menge"></v-text-field>
               <v-text-field v-model="newUnit" label="Einheit"></v-text-field>

             </v-row>

           </v-col>

           <v-col>
             <v-select
                     v-model="recipeData.courseIds"
                     :items="courses"
                     label="Kurse"
                     multiple
                     item-text="courseTitle"
                 ></v-select>
               </v-col>

        </v-row>
        Zutatenliste:
        <ul>
          <li v-for="(ingredient, index) in ingredients" :key="index">
            {{ ingredient.quantity }} - Menge: {{ ingredient.unit }} {{ ingredient.title }}
          </li>
        </ul>
        <v-btn type="submit">Rezept erstellen</v-btn>
      </v-form>
    </v-container>

  </div>
</template>

<style scoped>

</style>
