<script setup>

import {useCourseStore} from "@/stores/CourseStore";
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useRecipeStore} from "@/stores/RecipeStore";
import RecipeComponent from "@/components/RecipeComponent.vue";
import RecipeForm from "@/components/RecipeForm.vue";
import UserRecipeCard from "../../components/UserRecipeCard.vue";

const recipeStore = useRecipeStore()
const router = useRouter();
const isVisible = ref(false)
const err = false;

onMounted(() => {
  showRecipes();
  console.log('mounted');
});

async function showRecipes() {
  await recipeStore.showRecipes();
}

showRecipes();

</script>

<template>
 <div id="recipes-list" class="ma-2" >
    <h1 class="mx-auto">Deine verfügbaren Rezepte:</h1>
    <v-row class="d-flex ma-2">
      <v-col v-for="recipe in recipeStore.recipes" :key="recipe.recipeId" cols="12" sm="6" md="4" lg="3">
        <UserRecipeCard
            :key="recipe.recipeId"
            :title="recipe.title"
            :difficulty="recipe.difficulty"
            :preparation="recipe.preparation"
            :description="recipe.description"
            :recipeId="recipe.recipeId"
            :ingredients="recipe.ingredients"

        />
      </v-col>
    </v-row>
  </div>

</template>


//Backend hat eine Funktion um Rezepte von Usern zu bekommen