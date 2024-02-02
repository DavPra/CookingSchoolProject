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
                
                const recipeResponse = await axios.post(ApiUrl('/admin/addRecipe'), recipeData, {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                
                const createdRecipe = recipeResponse.data;
              
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
                
                // this.recipes = recipeResponse.data.recipes
                this.recipes = recipeResponse.data
                
                //const recipeId = recipeResponse.data.recipeId
            } catch (error) {
                console.error('Error loading recipes:', error);
                
            }
        },

        //in RecipeView(user) --- APPUSER
        async showUserRecipes(userId) {
           
            try {
                const userRecipeResponse = await axios.get(ApiUrl(`/users/recipes/${userId}`), {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                
                this.recipes = userRecipeResponse.data;
              
            } catch (error) {
                console.error('Error loading userRecipes:', error);
               
            }
        },

        async getCourseIds() {
            await this.courseStore.showCourses()
            //await courseStore.showCourses()
          
            return this.courseStore.courses.map(course => course.courseIds);
        },

        //in AdminRecipeView --- ADMIN
        async updateRecipe(recipeId, updatedRecipe) {
            await axios.put(ApiUrl(`/admin/updateRecipe/${recipeId}`), updatedRecipe, {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
          
            this.showRecipes()
        },

        //in AdminRecipeView --- ADMIN
        async deleteRecipe(recipeId) {
            await axios.delete(ApiUrl(`/admin/recipe/${recipeId}`), {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
            
            this.showRecipes()
        },
    }
})
