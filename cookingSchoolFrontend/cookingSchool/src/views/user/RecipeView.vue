<script setup>

import {useCourseStore} from "@/stores/CourseStore";
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useRecipeStore} from "@/stores/RecipeStore";
import UserRecipeCard from "../../components/UserRecipeCard.vue";

import jwtDecode from "jwt-decode";

const recipeStore = useRecipeStore()
const router = useRouter();
const isVisible = ref(false)
const err = false;

const userId = jwtDecode(localStorage.getItem("accessToken")).userId;

onMounted(() => {
  showUserRecipes();
  console.log('mounted');
});

async function showUserRecipes() {
  await recipeStore.showUserRecipes(userId);
}


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

  <v-btn
      class="mx-auto"
      color="primary"
      @click="recipeStore.showUserRecipes(userId)"

  >Test recipes</v-btn>

</template>


//Backend hat eine Funktion um Rezepte von Usern zu bekommen