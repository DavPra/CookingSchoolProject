import {defineStore} from "pinia";
import axios from "axios";
import {ref} from "vue";


export const useCourseStore = defineStore('course', {
    state: () => ({
        courses: [],
        userCourses: [],
        userRecipes: [],
    }),
    actions: {
        async showCourses() {
            try {
                const courseResponse = await axios.get('http://localhost:8082/admin/courses');
                this.courses = courseResponse.data;
                console.log('Courses loaded', this.courses);
            } catch (error) {
                console.error('Error loading courses:', error);
            }
        },
        async createCourse(data) {
            console.log('store 1', data)
            try {
                const courseData = {
                    courseTitle: data.courseTitle,
                    description: data.description,
                    teacher: data.teacher,
                    image: data.image,
                    startDate: data.startDate,
                    maxAttendants: data.maxAttendants,
                    price: data.price
                };
                console.log('store 2', data)
                const courseResponse = await axios.post('http://localhost:8082/admin/courses', courseData);
                const createdCourse = courseResponse.data;
                this.courses.push(courseResponse.data);
                console.log('Course created', courseResponse.data);
            } catch (error) {
                console.error('Error creating course:', error);
            }
        },
        async updateCourse(courseId, updatedCourse) {
            try {
                const courseResponse = await axios.put(`http://localhost:8082/admin/courses/${courseId}`, updatedCourse);
                const index = this.courses.findIndex(course => course.courseId === courseId);
                if (index !== -1) {
                    this.courses[index] = courseResponse.data;
                    console.log('Course updated', courseResponse.data);
                }
            } catch (error) {
                console.error('Error updating course:', error);
            }
        },
        async deleteCourse(courseId){
            const deleteResponse = await axios.delete('http://localhost:8082/admin/courses/'+courseId);
            console.log('Course deleted', courseId, deleteResponse.data);
            await this.showCourses();
        },


        async bookCourse(courseId, userId){
        const bookCourseResponse = await axios.put('http://localhost:8082/users/courses/'+courseId+'/book-course/'+userId)
        console.log('Course booked')
        await this.showCourses()
    },

    async showUserCourses(userId){
        console.log('store' + this.userCourses);
        
        console.log("UserId " + userId);
        const userCoursesResponse = await axios.get('http://localhost:8082/admin/users/'+userId);
        
        console.log("Array mit Courses " + userCoursesResponse);

            const userData = {
                userId: userCoursesResponse.data.userId,
                firstname: userCoursesResponse.data.firstname,
                lastname: userCoursesResponse.lastname,
                courses: userCoursesResponse.data.courses,
                recipes: userCoursesResponse.data.recipes
            };

            this.userCourses = userData.courses;
        
//for vielleicht zur view verschieben
    }
}   
});
