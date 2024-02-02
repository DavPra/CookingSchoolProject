<script setup>
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
  <div id="recipes-list" class="mt-6 mx-3">
    <h1 class="mx-5 mt-5 text-h4 ">Deine verfügbaren Rezepte:</h1>
    <v-row class="d-flex my-5 mx-2">
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