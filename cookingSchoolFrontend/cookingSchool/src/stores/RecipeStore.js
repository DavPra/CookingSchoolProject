import {defineStore} from "pinia";
import axios from "axios";
import {useCourseStore} from "@/stores/CourseStore";



export const useRecipeStore = defineStore('recipe', {
    state: () => ({
        recipes: [],
        courseStore: useCourseStore(),
    }),
    actions: {
        async showRecipes() {
            try {
                const recipeResponse = await axios.get('http://localhost:8082/admin/getAllRecipes');
                console.log(recipeResponse.data);
                this.recipes = recipeResponse.data;
                console.log('recipes geladen', recipeResponse.data);

            } catch (error) {
                console.error('Error loading recipes:', error);
                console.log('Response Body:', error.response.data);
            }
        },
        async addRecipe(data) {
            try {
                const recipeData = {
                    recipeId: data.recipeId,
                    title: data.title,
                    description: data.description,
                    difficulty: data.difficulty,
                    preparation: data.preparation,
                    courseIds: data.courseIds,
                    ingredients: data.ingredients
                };

                const courseIds = await this.getCourseIds();

                console.log(data);

                const recipeResponse = await axios.post('http://localhost:8082/admin/addRecipe', recipeData);
                console.log(recipeResponse.data);

                this.recipes.push(recipeResponse.data);
            } catch (error) {
                console.error('Error creating recipe:', error);
            }
        },
        async deleteRecipe(recipeId) {
            const deleteResponse = await axios.delete('http://localhost:8082/admin/recipe/' + recipeId)
            console.log('recipe deleted', recipeId)
            this.showRecipes()
        },

        async updateRecipe(recipeId) {
            const updateRecipeResponse = await axios.put('http://localhost:8082/admin/updateRecipe/' + recipeId)
            console.log('recipe updated')
            this.showRecipes()
        },
        async getCourseIds() {
          await this.courseStore.showCourses()
            //await courseStore.showCourses()
            console.log('kurse für rezepte geladen')
            return this.courseStore.courses.map(course => course.courseIds);

        }
    }
})