import {defineStore} from "pinia";
import axios from "axios";
import {createApiUrl} from "@/helper/ApiHelper";


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
        },
        async deleteCourse(courseId){
            const deleteResponse = await axios.delete('http://localhost:8082/admin/courses/'+courseId)
            console.log('Course deleted', courseId)
            this.showCourses()
        },
        async getCourses({commit}) {
            try {
                const response = await axios.get(createApiUrl('/admin/courses'));
                const courses = response.data;
                commit('setCourses', courses);
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        },
        async updateCourse({ commit }, updatedCourse) {
            commit('updateCourse', updatedCourse);
            try {
                const response = await axios.put(createApiUrl(`/courses/${updatedCourse.id}`), updatedCourse);
                console.log('Course updated:', response.data);
            } catch (error) {
                console.error('Error updating course:', error);
            }
        },
        mutations: {
            setCourses(state, courses) {
                state.courses = courses;
            },
            updateCourse(state, updatedCourse) {
                const index = state.courses.findIndex(course => course.id === updatedCourse.id);
                if (index !== -1) {
                    state.courses[index] = { ...updatedCourse };
                    axios.put(createApiUrl(`/courses/${updatedCourse.id}`), updatedCourse)
                        .then(response => {
                            console.log('Course updated:', response.data);
                        })
                        .catch(error => {
                            console.error('Error updating course:', error);
                        });
                }
            }
        }
}   
});
