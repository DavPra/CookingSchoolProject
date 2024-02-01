import {defineStore} from "pinia";
import axios from "axios";
import {ApiUrl} from "@/helper/ApiHelper";
import {useCourseStore} from "@/stores/CourseStore";

export const useRecipeStore = defineStore('recipe', {
    state: () => ({
        recipes: [],
        courseStore: useCourseStore(),
    }),
    actions: {

        //in AdminRecipeView --- ADMIN
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
                const recipeResponse = await axios.post(ApiUrl('/admin/addRecipe'), recipeData, {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                console.log(recipeResponse.data);
                const createdRecipe = recipeResponse.data;
                console.log('store recipe created!', recipeResponse.data);
                this.recipes.push(createdRecipe);
            } catch (error) {
                console.error('Error creating recipe:', error);
            }
        },

        //in AdminRecipeView --- ADMIN
        async showRecipes() {
            try {
                this.recipes = [];
                const recipeResponse = await axios.get(ApiUrl('/admin/getAllRecipes'), {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
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

        //in RecipeView(user) --- APPUSER
        async showUserRecipes(userId) {
            console.log('store' + this.userRecipes);
            try {
                const userRecipeResponse = await axios.get(ApiUrl(`/users/recipes/${userId}`), {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                console.log(userRecipeResponse.data);
                this.recipes = userRecipeResponse.data;
                console.log('userRecipes geladen', userRecipeResponse.data);
            } catch (error) {
                console.error('Error loading userRecipes:', error);
                console.log('Response Body:', error.response.data);
            }
        },

        async getCourseIds() {
            await this.courseStore.showCourses()
            //await courseStore.showCourses()
            console.log('kurse für rezepte geladen')
            return this.courseStore.courses.map(course => course.courseIds);
        },

        //in AdminRecipeView --- ADMIN
        async updateRecipe(recipeId, updatedRecipe) {
            await axios.put(ApiUrl(`/admin/updateRecipe/${recipeId}`), updatedRecipe, {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
            console.log('recipe updated')
            this.showRecipes()
        },

        //in AdminRecipeView --- ADMIN
        async deleteRecipe(recipeId) {
            await axios.delete(ApiUrl(`/admin/recipe/${recipeId}`), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
            console.log('recipe deleted', recipeId)
            this.showRecipes()
        },
    }
})
