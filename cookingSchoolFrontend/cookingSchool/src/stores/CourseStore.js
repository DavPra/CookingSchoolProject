import {defineStore} from "pinia";
import axios from "axios";


export const useCourseStore = defineStore('course', {
    state: () => ({
        courses: [],
        tags: []
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
                    startDate: data.startDate,
                    maxAttendants: data.maxAttendants,
                    price: data.price,
                    tags: [selectedTag]
                };

                console.log(data);

                const courseResponse = await axios.post('http://localhost:8082/admin/courses', courseData);
                console.log(courseResponse.data);

                this.courses.push(courseResponse.data);
            } catch (error) {
                console.error('Error creating course:', error);
            }
        },
        async deleteCourse(courseId) {
            const deleteResponse = await axios.delete('http://localhost:8082/admin/courses/' + courseId)
            console.log('Course deleted', courseId)
            await this.showCourses()
        },
        async updateCourse(courseId, updatedData) {
            try {
                const updateCourseResponse = await axios.put(`http://localhost:8082/admin/courses/${courseId}`, updatedData);
                console.log('Course updated:', updateCourseResponse.data);
                await this.showCourses();
            } catch (error) {
                console.error('Error updating course:', error);
                if (error.response) {
                    // Der Server hat geantwortet, aber mit einem Statuscode außerhalb des 2xx-Bereichs
                    console.error('Response data:', error.response.data);
                    console.error('Response status:', error.response.status);
                    console.error('Response headers:', error.response.headers);
                } else if (error.request) {
                    // Die Anfrage wurde gemacht, aber keine Antwort erhalten
                    console.error('No response received. Request:', error.request);
                } else {
                    // Etwas ist während der Anfragevorbereitung schief gelaufen
                    console.error('Error setting up the request:', error.message);
                }
            }

    },/*async getCourseById() {
            const getCourseByIdResponse = await axios.get('http://localhost:8082/admin/courses/');


            console.log('Course loaded', courseId);
            return getCourseByIdResponse.data;
        }*/
    async getTags() {
        try {
            const tagResponse = await axios.get('http://localhost:8082/admin/courseTag');
            console.log(tagResponse.data);
            this.tags = tagResponse.data;
            console.log('course geladen', tagResponse.data);

        } catch (error) {
            console.error('Error loading tags:', error);
            console.log('Response Body:', error.response.data);
        }
    },
}
});
