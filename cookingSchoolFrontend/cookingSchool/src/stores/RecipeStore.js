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
                this.recipes = [];
                const recipeResponse = await axios.get('http://localhost:8082/admin/getAllRecipes');
                console.log(recipeResponse.data);
               // this.recipes = recipeResponse.data.recipes
                this.recipes = recipeResponse.data
                console.log('recipes geladen', recipeResponse.data);
                //const recipeId = recipeResponse.data.recipeId



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
                    ingredients: data.ingredients,
                };

                const courseIds = await this.getCourseIds();
                console.log('storeData', data)

                const recipeResponse = await axios.post('http://localhost:8082/admin/addRecipe', recipeData);
                console.log(recipeResponse.data);
                const createdRecipe = recipeResponse.data;
                console.log('store recipe created!', recipeResponse.data);
                this.recipes.push(createdRecipe);

            } catch (error) {
                console.error('Error creating recipe:', error);
            }
        },
        async deleteRecipe(recipeId) {
            const deleteResponse = await axios.delete('http://localhost:8082/admin/recipe/' + recipeId)
            console.log('recipe deleted', recipeId)
            this.showRecipes()
        },

        async updateRecipe(recipeId, updatedRecipe) {
            const updateRecipeResponse = await axios.put('http://localhost:8082/admin/updateRecipe/' + recipeId, updatedRecipe)
            console.log('recipe updated')
            this.showRecipes()
        },
        async getCourseIds() {
          await this.courseStore.showCourses()
            //await courseStore.showCourses()
            console.log('kurse für rezepte geladen')
            return this.courseStore.courses.map(course => course.courseIds);

        },

        async showUserRecipes(userId) {
            console.log('store' + this.userRecipes);

            try {
                const userRecipeResponse = await axios.get('http://localhost:8082/users/recipes/' + userId);
                console.log(userRecipeResponse.data);
                this.recipes = userRecipeResponse.data;
                console.log('userRecipes geladen', userRecipeResponse.data);
            } catch (error) {
                console.error('Error loading userRecipes:', error);
                console.log('Response Body:', error.response.data);
            }
        }
    }
})
