<script setup>
import {useRecipeStore} from "@/stores/RecipeStore";
import {onMounted, ref, watchEffect} from "vue";
import {useCourseStore} from "@/stores/CourseStore";

const courseStore = useCourseStore()
const recipeStore = useRecipeStore()
const recipes = ref([]);
const dialog = ref(false);
const editMode = ref(false);
const recipeErr = ref('')
const rules = {
  required: value => !!value || 'Field is required',
}
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

watchEffect(() => {
  recipes.value = recipeStore.recipes;
});

onMounted(() => {
  fetchRecipes()
  recipeStore.showRecipes()

  loadCourses()
});

const loadCourses = async () => {
  try {
    console.log('Before loading courses');
    await courseStore.showCourses();
    console.log('After loading courses');

    console.log('Courses loaded:', courseStore.courses);

    courseOptions.value = courseStore.courses.map(course => ({
      courseId: course.courseId,
      title: course.courseTitle
    }));

    console.log('courseOptions:', courseOptions.value);
    console.log('courseID for recipes loaded');
  } catch (err) {
    console.error("Error loading courses:", err);
  }
};

const valid = ref(true)

const addIngredientRow = () => {
  if (!recipeData.value.ingredients) {
    recipeData.value.ingredients = []
  }
  recipeData.value.ingredients.push({title: '', quantity: '', unit: ''});
};

const fetchRecipes = async () => {
  await recipeStore.showRecipes()
  recipes.value = recipeStore.recipes;
}

const openDialog = () => {
  editMode.value = false;
  recipeData.value = {
    title: '',
    description: '',
    difficulty: 1,
    preparation: 0,
    selectedCourses: [],
    ingredients: [
      {title: '', quantity: '', unit: ''}
    ]
  }
  dialog.value = true;
};

const editRecipe = (recipe) => {
  editMode.value = true;
  recipeData.value = {...recipe};
  dialog.value = true;
};
const saveRecipe = async () => {
  try {
    console.log('Selected Courses:', recipeData.value.selectedCourses);
    console.dir(recipeData.value.selectedCourses);
    let courseIds = [];
    courseIds.push(recipeData.value.selectedCourses);

    console.log('Before update/create Recipe');
    if (editMode.value) {
      console.log('update Recipe called');

      console.log(recipeData.value.recipeId);

      await recipeStore.updateRecipe(recipeData.value.recipeId, {
        title: recipeData.value.title,
        description: recipeData.value.description,
        difficulty: recipeData.value.difficulty,
        preparation: recipeData.value.preparation,
        courseIds: courseIds,
        ingredients: recipeData.value.ingredients
      });
    } else {
      console.log('create Recipe called');

      await recipeStore.addRecipe({
        title: recipeData.value.title,
        description: recipeData.value.description,
        difficulty: recipeData.value.difficulty,
        preparation: recipeData.value.preparation,
        courseIds: courseIds,
        ingredients: recipeData.value.ingredients
      });
    }

    console.log('Before showRecipes');
    await recipeStore.showRecipes();
    console.log('After showRecipes');
    recipeErr.value=''
  } catch (error) {
    recipeErr.value = error.response.data.message;
    console.error('Error saving recipe:', error);
  } finally {
    closeDialog();
  }
};

async function deleteRecipe(recipeId) {
  console.log('recipeId delete', recipeId)
  await recipeStore.deleteRecipe(recipeId)
  await recipeStore.showRecipes()
}

const closeDialog = () => {
  dialog.value = false;
  valid.value = true;
  recipeData.value = {
    title: '',
    description: '',
    difficulty: 1,
    preparation: 0,
    selectedCourses: [],
    ingredients: [
      {title: '', quantity: '', unit: ''}
    ]
  };
};


</script>
<template>

  <v-container class="bg-brown-lighten-5 mt-4">

    <v-row>
      <v-card @click="openDialog" class="ma-3" width="330">
        <v-card-title class="mt-10 text-center">Estelle ein neues Rezept</v-card-title>
        <v-card-text class="text-center">
          <v-icon class="mt-6 mb-4" size="50" color="primary">mdi-plus</v-icon>
        </v-card-text>
      </v-card>


      <!-- Rezept card -->
      <v-col v-for="recipe in recipes" :key="recipe.id" cols="12" md="4" >

        <v-card width="330" >
          <v-card-title color="primary" >{{ recipe.title }}</v-card-title>
          <v-divider></v-divider>
          <v-card-subtitle class="py-2">Schwierigkeit: {{ recipe.difficulty }}</v-card-subtitle>
          <v-card-subtitle> Zubereitungs Zeit: {{ recipe.preparation }} Minuten</v-card-subtitle>
          <v-card-text v-if="recipe.courses && recipe.courses.length > 0">
            Kurs Nummer : {{ recipe.courses.map(course => course.courseId).join(', ') }}
          </v-card-text>
          <v-card-text class="ms-4">
            Zutaten:
            <ul>
              <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
                {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
              </li>
            </ul>
          </v-card-text>
          <v-card-text>Beschreibung: {{ recipe.description }}</v-card-text>
          <v-card-actions>
            <v-btn icon="mdi-pencil" size="small" color="primary" @click="editRecipe(recipe)"></v-btn>
            <v-btn icon="mdi-delete" size="small" color="primary" @click="deleteRecipe(recipe.recipeId)"></v-btn>
          </v-card-actions>

        </v-card>
      </v-col>
    </v-row>

  </v-container>

  <!-- Rezept Formular -->

  <v-dialog v-model="dialog" max-width="600">
    <v-card>
      <v-card-title>{{ editMode ? 'Bearbeite dein Rezept' : 'Erstelle ein neues Rezept' }}</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>

          <!-- Titel -->
          <v-row>
            <v-col>
              <v-text-field v-model="recipeData.title" :rules=[rules.required] label="Titel"></v-text-field>
            </v-col>

            <!-- Kurs -->

            <v-col>
              <v-select
                  v-model="recipeData.selectedCourses"
                  :items="courseOptions"
                  label="Kurse"
                  item-value="courseId"
              ></v-select>

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
              <v-text-field v-model="recipeData.difficulty" label="Schwierigkeitsgrad" max="5"
                            type="number"></v-text-field>
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


          <v-row>

          </v-row>

        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn @click="saveRecipe" :disabled="!valid">Speichern</v-btn>
        <v-btn @click="closeDialog">Zurück</v-btn>
      </v-card-actions>
      <v-alert v-if="recipeErr" closable type="error">{{ recipeErr }}</v-alert>
    </v-card>
  </v-dialog>


</template>

<style scoped>

</style>
