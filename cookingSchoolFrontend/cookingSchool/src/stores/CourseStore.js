import {defineStore} from "pinia";
import axios from "axios";
import {ApiUrl} from "@/helper/ApiHelper";

export const useCourseStore = defineStore('course', {
    state: () => ({
        courses: [],
        userCourses: [],
        userRecipes: [],
    }),
    actions: {

        //in AdminCourseView --- ADMIN
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
                const config = {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                    }
                }
                const courseResponse = await axios.post(ApiUrl('/admin/courses'), courseData, config);
                const createdCourse = courseResponse.data;
                this.courses.push(courseResponse.data);
                console.log('Course created', courseResponse.data);
                //bringt nichts  await this.showCourses()
            } catch (error) {
                console.error('Error creating course:', error);
            }
        },

        //in GuestCourseView, CourseCard, UserCourseView, AdminHomeView, AdminUserView, AdminRecipeView, AdminCourseView
        async showCourses() {
            try {
                const courseResponse = await axios.get(ApiUrl('/courses'));
                this.courses = courseResponse.data;
                console.log('Courses loaded', this.courses);
            } catch (error) {
                console.error('Error loading courses:', error);
            }
        },

        //in UserCourseView --- APPUSER
        async showUserCourses(userId) {
            console.log('store' + this.userCourses);
            console.log("UserId " + userId);
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const userCoursesResponse = await axios.get(ApiUrl(`/users/${userId}`), config);
            console.log("Array mit Courses " + userCoursesResponse);
            const userData = {
                userId: userCoursesResponse.data.userId,
                firstname: userCoursesResponse.data.firstname,
                lastname: userCoursesResponse.data.lastname,
                courses: userCoursesResponse.data.courses,
                recipes: userCoursesResponse.data.recipes
            };
            this.userCourses = userData.courses;
        },

        //in AdminCourseUserView --- ADMIN
        async getCourseById(courseId) {
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const courseByIdResponse = await axios.get(ApiUrl(`/admin/courses/${courseId}`), config);
            console.log('API response:', courseByIdResponse.data);
            return courseByIdResponse.data
        },

        //in AdminCourseView --- ADMIN
        async updateCourse(courseId, updatedCourse) {
            try {
                const config = {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                    }
                }
                const courseResponse = await axios.put(ApiUrl(`/admin/courses/${courseId}`), updatedCourse, config);
                const index = this.courses.findIndex(course => course.courseId === courseId);
                if (index !== -1) {
                    this.courses[index] = courseResponse.data;
                    console.log('Course updated', courseResponse.data);
                    // bringt nichts   await this.showCourses()
                }
            } catch (error) {
                console.error('Error updating course:', error);
            }
        },

        //TODO: Kathy - für admin und user? wo wird das eingebunden?
        //in CourseCard --- APPUSER
        async bookCourse(courseId, userId) {
            console.log('token= ' + localStorage.getItem('accessToken'));
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const bookCourseResponse = await axios.put(ApiUrl(`/users/${courseId}/book-course/${userId}`), config)
            console.log('Course booked')
            await this.showCourses()
        },

        //in AdminCourseView --- ADMIN
        async deleteCourse(courseId) {
            const config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('accessToken')
                }
            }
            const deleteResponse = await axios.delete(ApiUrl(`/admin/courses/${courseId}`), config);
            console.log('Course deleted', courseId, deleteResponse.data);
            await this.showCourses();
        }
    }
});
