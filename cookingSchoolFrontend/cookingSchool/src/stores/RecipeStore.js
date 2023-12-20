import { defineStore } from "pinia";
import axios from "axios";

export const useRecipeStore = defineStore('recipe', {
    state: () => ({
        recipes: []
    }),
    actions: {
        async fetchRecipes() {
            try {
                const response = await axios.get('http://localhost:8082/recipes');
                this.recipes = response.data;
            } catch (error) {
                console.error('Error loading recipes:', error);
            }
        }
    }
});
