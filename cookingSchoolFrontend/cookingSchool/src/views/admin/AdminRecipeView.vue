<script setup>

//TODO v-cklick-outside npm error
import {useCourseStore} from "@/stores/CourseStore";
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useRecipeStore} from "@/stores/RecipeStore";
import RecipeComponent from "@/components/RecipeComponent.vue";
import RecipeForm from "@/components/RecipeForm.vue";

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
  <!-- Übersicht aller Rezepte zum Bearbeiten für den Admin -->
  <RecipeForm></RecipeForm>
  <div id="recipes-list" class="ma-2" >
    <h1 class="mx-auto">Recipes:</h1>
    <v-row class="d-flex ma-2">
      <v-col v-for="recipe in recipeStore.recipes" :key="recipe.recipeId" cols="12" sm="6" md="4" lg="3"> -->
        <RecipeComponent
            :key="recipe.recipeId"
            :title="recipe.title"
            :difficulty="recipe.difficulty"
            :preparation="recipe.preparation"
            :description="recipe.description"
            :recipeId="recipe.recipeId"

        />
      </v-col>
    </v-row>
  </div>
</template>
