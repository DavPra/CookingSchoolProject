import {defineStore} from "pinia";
import axios from "axios";


export const useCourseStore = defineStore('course', {
    state: () => ({
        courses: []
    }),
    actions: {
        async showCourses() {
            try {
                const courseResponse = await axios.get('http://localhost:8082/admin/courses');
                console.log(courseResponse.data);
                this.courses = courseResponse.data;
                console.log('course geladen', courseResponse.data);

            } catch (error) {
                console.error('Error loading courses:', error);
                console.log('Response Body:', error.response.data);
            }
        },
        async createCourse(data) {
            try {
                const courseData = {
                    courseTitle: data.courseTitle,
                    description: data.description,
                    teacher: data.teacher,
                    startDate: new Date(data.startDate),
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
        },
        async deleteCourse(courseId){
            const deleteResponse = await axios.delete('http://localhost:8082/admin/courses/'+courseId);
            console.log('Course deleted', courseId, deleteResponse.data);
            this.showCourses();
        },
        async updateCourse(courseId){
            const updateCourseResponse = await axios.put('http://localhost:8082/admin/courses/'+courseId);
            console.log('Course updated', updateCourseResponse.data);
            this.showCourses();

    },

    async bookCourse(userId, courseId){
        const bookCourseResponse = await axios.post('http://localhost:8082/users/'+userId+'/book-course/'+courseId);
        const sentMail = await axios.post('http://localhost:8082/send-email/'+userId);
        console.log(bookCourseResponse.data, sentMail.data);
        this.showCourses();
    },

    async showUserCourses(userId){
        const userCoursesResponse = await axios.get('http://localhost:8082/admin/users/'+userId);
        console.log(userCoursesResponse.data);
        this.courses = userCoursesResponse.data.courses;
    }
}   
});
