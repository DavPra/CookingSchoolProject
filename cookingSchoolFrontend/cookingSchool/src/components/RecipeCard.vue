<script setup>
import { ref } from 'vue'
import { useRecipeStore } from '@/stores/RecipeStore'

const recipeStore = useRecipeStore()

const recipe = ref({
    title: '',
    description: '',
    diffiiculty: '',
    preparation: '',
    ingredients: '',
    tags: '',
})

async function getRecipe() {
    try {
        await recipeStore.getRecipe(recipe.value)
    } catch (err) {
        console.log(err)
    }
}

</script>

<template>

    recipe title: {{ recipe.title }}                             <br>
    recipe description: {{ recipe.description }}             <br>
    recipe difficulty: {{ recipe.difficulty }}         <br> 
    recipe preparation: {{ recipe.preparation }}     <br>
    recipe ingredients: {{ recipe.ingredients }}        <br>
    recipe tags: {{ recipe.tags }}

</template>