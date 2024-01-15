<script setup>
import {useRecipeStore} from "@/stores/RecipeStore";

const recipeStore = useRecipeStore();
const recipe = defineProps(['title','description', 'difficulty', 'preparation', 'ingredients' ,'recipeId'])
</script>

<template>
<v-card>
  <v-card-title>{{title}}</v-card-title>
  <v-card-subtitle>Difficulty: {{difficulty}} </v-card-subtitle>
  <v-card-subtitle> Preparation: {{preparation}}</v-card-subtitle>
  <v-card-text>Beschreibung: {{description}}</v-card-text>
</v-card>
</template>

<style scoped>

</style>
