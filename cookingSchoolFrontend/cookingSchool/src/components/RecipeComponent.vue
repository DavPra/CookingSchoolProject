<script setup>
import {useRecipeStore} from "@/stores/RecipeStore";

const recipeStore = useRecipeStore();
const recipe = defineProps(['title','description', 'difficulty', 'preparation', 'ingredients' ,'recipeId'])

async function deleteRecipe(recipeId){
  await recipeStore.deleteRecipe(recipeId)
}
</script>

<template>
<v-card>
  <v-card-title>{{title}}</v-card-title>
  <v-card-subtitle>Difficulty: {{difficulty}} </v-card-subtitle>
  <v-card-subtitle> Preparation: {{preparation}}</v-card-subtitle>
  <v-card-text>Beschreibung: {{description}}</v-card-text>
<v-card-text>{{ingredients.title}} {{ingredients.unit}} {{ingredients.quantity}}</v-card-text>
  <v-card-actions>
    <v-btn icon="mdi-pencil" size="small" @click="editRecipe"></v-btn>
    <v-btn icon="mdi-delete" size="small" @click="deleteRecipe"></v-btn>
  </v-card-actions>
</v-card>
</template>

<style scoped>

</style>
