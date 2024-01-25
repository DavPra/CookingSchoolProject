<script setup>
import { ref, onMounted } from 'vue';
import { useRecipeStore } from '@/stores/RecipeStore';

const recipeStore = useRecipeStore();
const recipes = ref([]);
const dialog = ref(false);
const editMode = ref(false);
const editedRecipe = ref({
  title: '',
  description: '',
  difficulty: 1,
  preparation: 0,
  selectedCourses: [],
  ingredients: [
    { title: '', quantity: '', unit: '' }
  ]
});
const validRecipe = ref(true);

onMounted(() => {
  recipeStore.showRecipes()
  fetchRecipes();
});

const fetchRecipes = async () => {
  await recipeStore.showRecipes();
  recipes.value = recipeStore.recipes;
};

const openRecipeDialog = () => {
  editMode.value = false;
  editedRecipe.value = {
    title: '',
    description: '',
    difficulty: 1,
    preparation: 0,
    selectedCourses: [],
    ingredients: [
      { title: '', quantity: '', unit: '' }
    ]
  };
  dialog.value = true;
};

const editRecipe = (recipe) => {
  editMode.value = true;
  editedRecipe.value = { ...recipe };
  dialog.value = true;
};

const saveRecipe = () => {
  if (editMode.value) {
    // Update existing recipe
    const index = recipes.value.findIndex((recipe) => recipe.recipeId === editedRecipe.value.recipeId);
    recipes.value[index] = { ...editedRecipe.value };
  } else {
    // Add new recipe
    recipes.value.push({ id: recipes.value.length + 1, ...editedRecipe.value });
  }

  closeDialog();
};

const closeDialog = () => {
  dialog.value = false;
  validRecipe.value = true;
  editedRecipe.value = {
    title: '',
    description: '',
    difficulty: 1,
    preparation: 0,
    selectedCourses: [],
    ingredients: [
      { title: '', quantity: '', unit: '' }
    ]
  };
};

const deleteRecipe = (recipeId) => {
  const index = recipes.value.findIndex((recipe) => recipe.id === recipeId);
  recipes.value.splice(index, 1);
};
</script>

<template>
  <div>
    <h1>Recipes</h1>
    <v-container>
      <v-row>
        <v-col v-for="recipe in recipes" :key="recipe.recipeId" cols="12" md="4">
          <v-card>
            <v-card-title>{{ recipe.title }}</v-card-title>
            <v-card-subtitle>Difficulty: {{ recipe.difficulty }}</v-card-subtitle>
            <v-card-subtitle>Preparation: {{ recipe.preparation }}</v-card-subtitle>
            <v-card-subtitle>Kurs Ids: {{ recipe.courseIds }}</v-card-subtitle>
            <v-card-text>
              Ingredients:
              <ul>
                <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
                  {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
                </li>
              </ul>
            </v-card-text>
            <v-card-text>Description: {{ recipe.description }}</v-card-text>
            <v-card-actions>
              <v-btn icon="mdi-pencil" size="small" @click="editRecipe"></v-btn>
              <v-btn icon="mdi-delete" size="small" @click="deleteRecipe"></v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
      <v-dialog v-model="dialog" max-width="500">
        <v-card>
          <v-card-title v-if="!editMode.value">Add New Recipe</v-card-title>
          <v-card-title v-else>Edit Recipe</v-card-title>
          <v-card-text>
            <v-form ref="recipeForm" v-model="validRecipe" lazy-validation>
              <v-text-field v-model="editedRecipe.title" label="Title" required></v-text-field>
              <v-text-field v-model="editedRecipe.difficulty" label="Difficulty" type="number"></v-text-field>
              <v-text-field v-model="editedRecipe.preparation" label="Preparation" type="number"></v-text-field>
              <v-row v-for="(ingredient, index) in editedRecipe.ingredients" :key="index">
                <v-col>
                  <v-text-field v-model="ingredient.title" label="Ingredient"></v-text-field>
                </v-col>
                <v-col>
                  <v-text-field v-model="ingredient.quantity" label="Quantity"></v-text-field>
                </v-col>
                <v-col>
                  <v-text-field v-model="ingredient.unit" label="Unit"></v-text-field>
                </v-col>
              </v-row>
              <v-btn @click="addIngredientRow">Add New Ingredient</v-btn>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-btn @click="saveRecipe" :disabled="!validRecipe">Save</v-btn>
            <v-btn @click="closeDialog">Cancel</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-btn @click="openRecipeDialog">Add New Recipe</v-btn>
    </v-container>
  </div>
</template>

<style scoped>

</style>
