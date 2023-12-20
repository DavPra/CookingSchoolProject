<script setup>
import { computed, onMounted } from 'vue';
import { useRecipeStore } from '@/stores/RecipeStore'; // Adjust the path

const recipeStore = useRecipeStore();

// Fetch recipes when the component is mounted
onMounted(() => {
  recipeStore.fetchRecipes();
});

// Access the recipes from the store
const recipes = computed(() => recipeStore.recipes);
</script>

<template>
  <div>
    <v-container>
      <v-row>
        <v-col
          v-for="recipe in recipes"
          :key="recipe.id"
          cols="12"
          md="6"
        >
          <v-card class="mb-3">
            <v-card-title>{{ recipe.title }}</v-card-title>
            <v-card-text>
              <h4>Ingredients:</h4>
              <ul>
                <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
                  {{ ingredient }}
                </li>
              </ul>
              <h4>Instructions:</h4>
              <p>{{ recipe.instructions }}</p>
              <!-- Add more recipe details to display -->
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style>
/* Add styling as per your requirements using Vuetify classes or custom styles */
</style>
