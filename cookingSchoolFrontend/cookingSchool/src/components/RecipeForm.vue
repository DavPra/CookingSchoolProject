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
  ingredients: [
    {title: '', quantity: '', unit: ''}
  ]
})
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

const addIngredientRow = () => {
  if (!recipeData.value.ingredients) {
    recipeData.value.ingredients = []
  }
  recipeData.value.ingredients.push({title: '', quantity: '', unit: ''});
};


loadCourses();


</script>

<template>
  <v-sheet elevation="4" width="80%" class="mx-auto mt-5">
    <h1>Erstelle ein neues Rezept:</h1>
    <v-container>
      <v-form @submit.prevent="addRecipe">

        <!-- Titel -->
        <v-row>
          <v-col>
            <v-text-field v-model="recipeData.title" label="Titel"></v-text-field>
          </v-col>

          <!-- Kurs -->

          <v-col>
            <v-select v-model="recipeData.selectedCourses" :items="courseOptions" label="Kurs"
                      item-value="courseId"></v-select>
          </v-col>
        </v-row>

        <!-- Beschreibung -->
        <v-row>
          <v-col>
            <v-textarea v-model="recipeData.description" label="Beschreibung" rows="5"></v-textarea>
          </v-col>
        </v-row>

        <!-- Schwierigkeitsgrad -->
        <v-row>
          <v-col>
            <v-text-field v-model="recipeData.difficulty" label="Schwierigkeitsgrad" type="number"></v-text-field>
          </v-col>


          <!-- Vorbereitungszeit -->

          <v-col>
            <v-text-field v-model="recipeData.preparation" label="Vorbereitungszeit (in Minuten)"
                          type="number"></v-text-field>
          </v-col>
        </v-row>

        <!-- Zutaten -->
        <v-row v-for="(ingredient, index) in recipeData.ingredients" :key="index">
          <v-col>
            <v-text-field v-model="ingredient.title" label="Zutat"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field v-model="ingredient.quantity" label="Menge"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field v-model="ingredient.unit" label="Einheit"></v-text-field>
          </v-col>
        </v-row>

        <!-- Neue Zutat hinzufügen -->
        <v-row>
          <v-col>
            <v-btn @click="addIngredientRow">Neue Zutat hinzufügen</v-btn>
          </v-col>
        </v-row>


        <!-- Zutatenliste -->
        <v-row>
          <v-col>
            <div>
              Zutatenliste:
              <ul>
                <li v-for="(ingredient, index) in recipeData.ingredients" :key="index">
                  {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
                </li>
              </ul>
            </div>
          </v-col>
        </v-row>

        <!-- Submit-Button -->
        <v-row>
          <v-col>
            <v-btn type="submit">Rezept erstellen</v-btn>
          </v-col>
        </v-row>

      </v-form>
    </v-container>
  </v-sheet>
</template>

<style scoped>

</style>
