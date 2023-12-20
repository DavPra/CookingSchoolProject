import {defineStore} from "pinia";
import axios from "axios";


export const useRecipeStore = defineStore('course', {
    state: () => ({
        repices: []
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
            }
        },
        /*async createCourse(data) {
            try {
                const courseData = {
                    courseTitle: data.courseTitle,
                    description: data.description,
                    teacher: data.teacher,
                    startDate: data.startDate,
                    maxAttendants: data.maxAttendants,
                    price: data.price
                };

                console.log(data);

                const courseResponse = await axios.post('http://localhost:8082/admin/courses', courseData);
                console.log(courseResponse.data);

                this.courses.push(courseResponse.data);
            } catch (error) {
                console.error('Error creating course:', error);
            }
        } */
    }
});