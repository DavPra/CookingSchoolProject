<script setup>
import {toRaw, ref} from "vue";

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
const show = ref(false);

console.log('ingredients:', ingredients);
</script>

<template>
  <v-card>
    <v-img
        cover
        height="250"
        src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>
    <v-card-title>{{ title }}</v-card-title>
    <v-divider></v-divider>
    <v-card-subtitle>Schwierigkeitsgrad: {{ difficulty }}</v-card-subtitle>
    <v-card-subtitle> Zubereitungszeit: {{ preparation }}</v-card-subtitle>
    <v-card-text class="ms-4">
      Zutaten:
      <ul>
        <li v-for="(ingredient, index) in ingredients" :key="index">
          {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
        </li>
      </ul>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary">Beschreibung</v-btn>
      <v-spacer></v-spacer>
      <v-btn
          :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          @click="show = !show">
      </v-btn>
    </v-card-actions>
    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>
        <v-card-text>{{ description }}</v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>