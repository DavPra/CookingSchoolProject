<script setup>
import {useRecipeStore} from "@/stores/RecipeStore";
import {toRaw} from "vue";

const recipeStore = useRecipeStore();
//const recipe = defineProps(['title','description', 'difficulty', 'preparation', 'ingredients' ,'recipeId'])
const {
  title,
  description,
  difficulty,
  preparation,
  ingredients,
  recipeId,
  courseIds
} = defineProps(['title', 'description', 'difficulty', 'preparation', 'ingredients', 'recipeId', 'courseIds']);
const getIngredients = toRaw(ingredients);
console.log('ingredients:', ingredients);
const deleteRecipe = async () => {
  console.log('Deleting recipe with ID:', recipeId);
  try {
    await recipeStore.deleteRecipe(recipeId);
    console.log('Recipe deleted successfully');
  } catch (error) {
    console.error('Error deleting recipe:', error.message);
    if (error.response) {
      console.error('Server response:', error.response.data);
    }
  }
}
</script>

<template>
  <v-card>
    <v-card-title>{{ title }}</v-card-title>
    <v-card-subtitle>Schwierigkeitsgrad: {{ difficulty }}</v-card-subtitle>
    <v-card-subtitle> Zubereitungszeit: {{ preparation }}</v-card-subtitle>
    <v-card-subtitle> Kurs Ids: {{ courseIds }}</v-card-subtitle>
    <v-card-text>
      Zutaten:
      <ul>
        <li v-for="(ingredient, index) in ingredients" :key="index">
          {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
        </li>
      </ul>
    </v-card-text>
    <v-card-text>Beschreibung: {{ description }}</v-card-text>
    <v-card-actions>
      <v-btn icon="mdi-pencil" size="small" @click="editRecipe"></v-btn>
      <v-btn icon="mdi-delete" size="small" @click="deleteRecipe"></v-btn>
    </v-card-actions>
  </v-card>
</template>

<style scoped>

</style>
