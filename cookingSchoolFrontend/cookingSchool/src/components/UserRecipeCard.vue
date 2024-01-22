<script setup>
import {useRecipeStore} from "@/stores/RecipeStore";
import {toRaw} from "vue";

const recipeStore = useRecipeStore();
//const recipe = defineProps(['title','description', 'difficulty', 'preparation', 'ingredients' ,'recipeId'])
const { title, description, difficulty, preparation, ingredients, recipeId ,courseIds} = defineProps(['title', 'description', 'difficulty', 'preparation', 'ingredients', 'recipeId','courseIds']);
const getIngredients = toRaw(ingredients);
console.log('ingredients:', ingredients);

</script>

<template>
<v-card>

    <v-img
            cover
            height="250"
            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img>

  <v-card-title>{{title}}</v-card-title>
  <v-card-subtitle>Difficulty: {{difficulty}} </v-card-subtitle>
  <v-card-subtitle> Preparation: {{preparation}}</v-card-subtitle>
  <v-card-text>
    Zutaten:
    <ul>
      <li v-for="(ingredient, index) in ingredients" :key="index">
        {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
      </li>
    </ul>
  </v-card-text>
  <v-card-text>Beschreibung: {{description}}</v-card-text>
</v-card>
</template>

<style scoped>

</style>
